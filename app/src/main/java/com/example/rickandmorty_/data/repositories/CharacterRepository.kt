package com.example.rickandmorty_.data.repositories

import com.example.rickandmorty_.base.BaseRepository
import com.example.rickandmorty_.data.remote.apiserices.CharacterApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharacterApiService)
    : BaseRepository() {
    fun fetchCharacters(page: Int) = doRequest {
        service.fetchCharacters(page)
    }
    fun fetchSingleCharacter(id : Int) = doRequest {
        service.fetchCharacterId(id)

    }
}

