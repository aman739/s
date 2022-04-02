package com.example.rickandmorty_.ui.fragments.episodes

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.rickandmorty_.base.BaseViewModel
import com.example.rickandmorty_.data.repositories.EpisodesRepository
import com.example.rickandmorty_.models.RickAndMortyEpisodes
import com.example.rickandmorty_.models.RickAndMortyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EpisodesViewModel @Inject constructor(
    private val repository: EpisodesRepository
) : BaseViewModel() {
    var pagee = 1
    var isLoading: Boolean = false

    private val _episodesState = MutableLiveData<RickAndMortyResponse<RickAndMortyEpisodes>>()
    val episodesState: LiveData<RickAndMortyResponse<RickAndMortyEpisodes>> = _episodesState

    private val _episodesLocaleState =
        MutableLiveData<List<RickAndMortyEpisodes>>()
    val episodesLocateState: LiveData<List<RickAndMortyEpisodes>> = _episodesLocaleState

//    fun fetchEpisodes() = viewModelScope.launch {
//        repository.fetchEpisodes(pagee).collect {
//            when (it) {
//                is Resource.Loading -> {
//                    isLoading = true
//                }
//                is Resource.Error -> {
//                    android.util.Log.e("bankai", it.message.toString())
//                }
//                is Resource.Success -> {
//                    if (it.data?.info?.next != null) {
//                        pagee++
//                        isLoading = false
//                        it.data?.let { it ->
//                            _episodesState.postValue(it)
//                        }
//                    }
//                }
//            }
//        }
//    }

    fun fetchEpisodes() = viewModelScope.launch {
        isLoading = true
        repository.fetchEpisodes(pagee).collectFlow(_episodesState) {
            if (it.info.next != null) {
                isLoading = false
                pagee++
            }
        }
    }

    fun getEpisodes() =
        repository.getEpisodes().collectFlow(_episodesLocaleState, {})
}