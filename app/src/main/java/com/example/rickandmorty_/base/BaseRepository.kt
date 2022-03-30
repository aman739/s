package com.example.rickandmorty_.base

import com.example.rickandmorty_.common.resource.Resource
import kotlinx.coroutines.flow.flow
import java.io.IOException

abstract class BaseRepository {

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (e: IOException) {
            emit(
                Resource.Error(data = null, message = e.localizedMessage ?: "error")
            )
        }
    }
}
