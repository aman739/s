package com.example.rickandmorty_.ui.fragments.characters

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.common.resource.Resource
import com.example.rickandmorty_.data.repositories.CharacterRepository
import com.example.rickandmorty_.models.RickAndMortyCharacter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharacterRepository
) : BaseViewModel() {
   private  var page = 0
    var isLoading: Boolean = false

    private val _characterState =
        MutableLiveData<ArrayList<RickAndMortyCharacter>>()
    val characterState: LiveData<ArrayList<RickAndMortyCharacter>> =
        _characterState

    fun fetchCharacters() {
        isLoading = true
        viewModelScope.launch {
            repository.fetchCharacters(page).collect {
                when (it) {
                    is Resource.Loading -> {
                        isLoading = true
                    }
                    is Resource.Error -> {
                        Log.e("Error character", it.message.toString())
                    }
                    is Resource.Success -> {
                        if (it.data?.info?.next != null) {
                            isLoading = false
                            _characterState.postValue(it.data.results)
                            page++
                        }
                    }
                }
            }
        }
    }
    init {
        if (_characterState.value == null) {
            fetchCharacters()
        }
    }
}