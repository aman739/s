package com.example.rickandmorty_.data.remote.apiserices

import com.example.rickandmorty_.models.RickAndMortyCharacter
import com.example.rickandmorty_.models.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterApiService {

    @GET("api/character")
    suspend fun fetchCharacters(
        @Query("page") page: Int
    ): RickAndMortyResponse<RickAndMortyCharacter>


    @GET("api/character/{id}")
    suspend fun fetchCharacterId(
        @Path("id") id: Int,
    ): RickAndMortyCharacter
}