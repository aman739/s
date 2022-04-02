package com.example.rickandmorty_.ui.fragments.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.data.repositories.EpisodesRepository
import com.example.rickandmorty_.models.RickAndMortyEpisodes
import com.example.rickandmorty_.models.RickAndMortyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: EpisodesRepository
) : BaseViewModel() {
    var page = 0
    var isLoading: Boolean = false

    private val _episodesState = MutableLiveData<RickAndMortyResponse<RickAndMortyEpisodes>>()
    val episodesState: LiveData<RickAndMortyResponse<RickAndMortyEpisodes>> = _episodesState

    private val _episodesLocaleState =
        MutableLiveData<List<RickAndMortyEpisodes>>()
    val episodesLocateState: LiveData<List<RickAndMortyEpisodes>> = _episodesLocaleState

    fun fetchEpisodes() = viewModelScope.launch {
        isLoading = true
        repository.fetchEpisodes(page).collectFlow(_episodesState) {
            if (it.info.next != null) {
                isLoading = false
                page++
            }
        }
    }
    fun getEpisodes() =
        repository.getEpisodes().collectFlow(_episodesLocaleState, {})

}