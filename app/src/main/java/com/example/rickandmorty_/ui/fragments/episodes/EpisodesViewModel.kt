package com.example.rickandmorty_.ui.fragments.episodes

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.data.repositories.EpisodesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository:
    EpisodesRepository,
) :
    BaseViewModel() {
    fun fetchEpisodes() = repository.fetchEpisodes().cachedIn(viewModelScope)
}
