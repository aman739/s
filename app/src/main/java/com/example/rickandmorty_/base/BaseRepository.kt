package com.example.rickandmorty_.base

import com.example.rickandmorty_.common.resource.Resource
import kotlinx.coroutines.flow.flow

abstract class BaseRepository {

    protected fun <T> doRequest(
        request: suspend () -> T,
        writeDatabase: suspend (data: T) -> Unit,
        ) =
        flow {
            emit(Resource.Loading())
            try {
                writeDatabase(request())
                emit(Resource.Success(data = request()))
            } catch (ioExeption: Exception) {
                emit(
                    Resource.Error(
                        data = null, message = ioExeption.localizedMessage ?: "bankai"
                    )
                )
            }
        }

    protected fun <T> doRequest(request: suspend () -> T) = flow {
        emit(Resource.Loading())
        try {
            emit(Resource.Success(data = request()))
        } catch (ioExeption: Exception) {
            emit(
                Resource.Error(
                    data = null, message = ioExeption.localizedMessage ?: "bankai"
                )
            )
        }
    }
}