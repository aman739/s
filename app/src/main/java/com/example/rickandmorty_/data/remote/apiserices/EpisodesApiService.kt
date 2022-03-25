package com.example.rickandmorty_.data.remote.apiserices

import com.example.rickandmorty_.models.RickAndMortyEpisodes
import com.example.rickandmorty_.models.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface EpisodesApiService {
    @GET("api/episode")
    suspend fun fetchEpisodes(@Query("page") page: Int)
            : RickAndMortyResponse<RickAndMortyEpisodes>

}