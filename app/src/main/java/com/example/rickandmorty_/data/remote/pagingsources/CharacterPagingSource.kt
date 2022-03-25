package com.example.rickandmorty_.data.remote.pagingsources

import android.net.Uri
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickandmorty_.data.remote.apiserices.CharacterApiService
import com.example.rickandmorty_.models.RickAndMortyCharacter
import retrofit2.HttpException
import java.io.IOException

const val CHARACTER_KEY = 1

class CharacterPagingSource(private val service: CharacterApiService) :
    PagingSource<Int, RickAndMortyCharacter>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, RickAndMortyCharacter> {
        val page = params.key ?: CHARACTER_KEY
        return try {
            val response = service.fetchCharacters(page)
            val nextPageNumber = if (response.info.next == null) {
                null
            } else Uri.parse(response.info.next).getQueryParameter("page")!!.toInt()
            return LoadResult.Page(
                data = response.results,
                prevKey = null,
                nextKey = nextPageNumber
            )
        } catch (e: IOException) {
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, RickAndMortyCharacter>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}