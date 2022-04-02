package com.example.rickandmorty_.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty_.common.resource.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    protected fun <T> Flow<Resource<T>>.collectFlow(
        state: MutableLiveData<T>,
        data: (data: T) -> Unit?

    ) {
        viewModelScope.launch {
            collect {
                when (it) {
                    is Resource.Loading -> {
                    }
                    is Resource.Error -> {
                        android.util.Log.e("bankai", it.message.toString())
                    }
                    is Resource.Success -> {
                        it.data?.let { data -> data(data) }
                        state.postValue(it.data)
                    }
                }
            }
        }
    }
}