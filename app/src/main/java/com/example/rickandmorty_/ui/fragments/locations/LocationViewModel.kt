package com.example.rickandmorty_.ui.fragments.locations

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.common.resource.Resource
import com.example.rickandmorty_.data.repositories.LocationsRepository
import com.example.rickandmorty_.models.RickAndMortyLocation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository: LocationsRepository
) : BaseViewModel() {
    private  var page = 1
    var isLoading: Boolean = false

    private val _locationState =
        MutableLiveData<ArrayList<RickAndMortyLocation>>()

    val locationState: LiveData<ArrayList<RickAndMortyLocation>> =
        _locationState

    fun fetchLocation() {
        isLoading = true
        viewModelScope.launch {
            repository.fetchLocations(page).collect {
                when (it) {
                    is Resource.Loading -> {
                        isLoading = true
                    }
                    is Resource.Error -> {
                        Log.e("Error location", it.message.toString())
                    }
                    is Resource.Success -> {
                        if (it.data?.info?.next != null) {
                            isLoading = false
                            _locationState.postValue(it.data.results)
                            page++
                        }
                    }
                }
            }
        }
    }
    init {
        if (_locationState.value == null) {
            fetchLocation()
        }
    }

}