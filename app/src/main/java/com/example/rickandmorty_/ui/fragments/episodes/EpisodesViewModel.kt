package com.example.rickandmorty_.ui.fragments.episodes

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.common.resource.Resource
import com.example.rickandmorty_.data.repositories.EpisodesRepository
import com.example.rickandmorty_.models.RickAndMortyEpisodes
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: EpisodesRepository
) : BaseViewModel() {
    private  var page = 1
    var isLoading: Boolean = false

    private val _episodesState =
        MutableLiveData<ArrayList<RickAndMortyEpisodes>>()
    val episodesState: LiveData<ArrayList<RickAndMortyEpisodes>> =
        _episodesState
    fun fetchEpisodes() {
        isLoading = true
        viewModelScope.launch {
            repository.fetchEpisodes(page).collect {
                when (it) {
                    is Resource.Loading -> {
                        isLoading = true
                    }
                    is Resource.Error -> {
                        Log.e("Error episodes", it.message.toString())
                    }
                    is Resource.Success -> {
                        if (it.data?.info?.next != null) {
                            isLoading = false
                            _episodesState.postValue(it.data.results)
                            page++
                        }
                    }
                }
            }
        }
    }
    init {
        if (_episodesState.value == null) {
            fetchEpisodes()
        }
    }
}