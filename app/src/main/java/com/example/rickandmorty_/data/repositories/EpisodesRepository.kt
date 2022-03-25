package com.example.rickandmorty_.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty_.data.remote.apiserices.EpisodesApiService
import com.example.rickandmorty_.data.remote.pagingsources.EpisodesPagingSource
import javax.inject.Inject

class EpisodesRepository @Inject constructor(private val service: EpisodesApiService) {

    fun fetchEpisodes() = Pager(
        PagingConfig(pageSize = 20)
    ) {
        EpisodesPagingSource(service)
    }.flow
}