package com.example.rickandmorty_.ui.fragments.locations

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.data.repositories.LocationsRepository
import com.example.rickandmorty_.models.RickAndMortyLocation
import com.example.rickandmorty_.models.RickAndMortyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationsRepository
) : BaseViewModel() {
    var page = 0
    var isLoading: Boolean = false

    private val _locationState = MutableLiveData<RickAndMortyResponse<RickAndMortyLocation>>()
    val locationState: LiveData<RickAndMortyResponse<RickAndMortyLocation>> = _locationState

    private val _locationLocaleState =
        MutableLiveData<List<RickAndMortyLocation>>()
    val locationLocateState: LiveData<List<RickAndMortyLocation>> = _locationLocaleState

    fun fetchLocations() = viewModelScope.launch {
        isLoading = true
        repository.fetchLocation(page).collectFlow(_locationState) {
            if (it.info.next != null) {
                isLoading = false
                page++
            }
        }
    }
    fun getEpisodes() =
        repository.getLocation().collectFlow(_locationLocaleState, {})
}