package com.example.rickandmorty_.data.remote.apiserices

import com.example.rickandmorty_.models.RickAndMortyLocation
import com.example.rickandmorty_.models.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationsApiService {
    @GET("api/location")
    suspend fun fetchLocations(@Query("page") page: Int)
            : RickAndMortyResponse<RickAndMortyLocation>
}