package com.example.rickandmorty_.ui.fragments.locations

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.data.repositories.LocationsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LocationViewModel @Inject constructor(
    private val repository:
    LocationsRepository,
) : BaseViewModel() {
    fun fetchLocations() = repository.fetchLocation().cachedIn(viewModelScope)
}