package com.example.rickandmorty_.data.repositories

import com.example.rickandmorty_.base.BaseRepository
import com.example.rickandmorty_.data.remote.apiserices.LocationsApiService
import javax.inject.Inject

class LocationsRepository @Inject constructor(
    private val service: LocationsApiService
)
    : BaseRepository() {
    fun fetchLocations(page: Int) = doRequest {
        service.fetchLocations(page)
    }
}