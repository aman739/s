package com.example.rickandmorty_.ui.fragments.characters

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.data.repositories.CharacterRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository
) : BaseViewModel() {
    fun fetchCharacters() = repository.fetchCharacters().cachedIn(viewModelScope)
}