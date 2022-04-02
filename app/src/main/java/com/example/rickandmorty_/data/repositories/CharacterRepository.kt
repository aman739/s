package com.example.rickandmorty_.data.repositories

import com.example.rickandmorty_.base.BaseRepository
import com.example.rickandmorty_.data.locale.db.daos.CharacterDao
import com.example.rickandmorty_.data.remote.apiserices.CharacterApiService
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val service: CharacterApiService,
    private val characterDao: CharacterDao
) :
    BaseRepository() {
    fun fetchCharacters(page: Int) = doRequest(
        {
            service.fetchCharacters(page)
        },
        {
                characters ->
            characterDao.insertAll(*characters.results.toTypedArray())
        }
    )

    fun getCharacters() = doRequest {
        characterDao.getAll()
    }

    fun fetchSingleCharacter(id: Int) = doRequest {
        service.fetchCharacterId(id)
    }
}



