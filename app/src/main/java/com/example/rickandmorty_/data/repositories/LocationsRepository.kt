package com.example.rickandmorty_.data.repositories

import com.example.rickandmorty_.base.BaseRepository
import com.example.rickandmorty_.data.locale.db.daos.LocationDao
import com.example.rickandmorty_.data.remote.apiserices.LocationsApiService
import javax.inject.Inject

class LocationsRepository @Inject constructor(
    private val service: LocationsApiService,
    private val locationDao: LocationDao
) :
    BaseRepository() {
    fun fetchLocation(page: Int) = doRequest(
        {
            service.fetchLocations(page)
        },
        {
                location ->
            locationDao.insertAll(*location.results.toTypedArray())
        }
    )

    fun getLocation() = doRequest {
        locationDao.getAll()
    }
}
