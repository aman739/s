package com.example.rickandmorty_.data.repositories

import androidx.paging.Pager
import androidx.paging.PagingConfig
import com.example.rickandmorty_.data.remote.apiserices.LocationsApiService
import com.example.rickandmorty_.data.remote.pagingsources.LocationPagingSource
import javax.inject.Inject

class LocationsRepository @Inject constructor(private val service: LocationsApiService) {

    fun fetchLocation() = Pager(
        PagingConfig(pageSize = 20)
    ) {
        LocationPagingSource(service)
    }.flow
}