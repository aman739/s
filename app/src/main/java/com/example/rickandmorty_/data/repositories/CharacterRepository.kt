package com.example.rickandmorty_.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty_.data.remote.apiserices.CharacterApiService
import com.example.rickandmorty_.data.remote.pagingsources.CharacterPagingSource
import javax.inject.Inject

class CharacterRepository @Inject constructor(private val service: CharacterApiService) {
    fun fetchCharacters() = Pager(
        PagingConfig(pageSize = 20)
    ) {
        CharacterPagingSource(service)
    }.flow

}

