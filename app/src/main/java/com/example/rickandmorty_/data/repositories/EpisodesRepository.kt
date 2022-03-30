package com.example.rickandmorty_.data.repositories

import com.example.rickandmorty_.base.BaseRepository
import com.example.rickandmorty_.data.remote.apiserices.EpisodesApiService
import javax.inject.Inject

class EpisodesRepository @Inject constructor(
    private val service: EpisodesApiService
)
    : BaseRepository() {
    fun fetchEpisodes(page: Int) = doRequest {
        service.fetchEpisodes(page)
    }
}