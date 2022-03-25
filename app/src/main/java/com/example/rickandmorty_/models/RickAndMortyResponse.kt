package com.example.rickandmorty_.models

import com.google.gson.annotations.SerializedName

data class RickAndMortyResponse<T>(
    @SerializedName("id")
    val id: Int,

    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val results: ArrayList<T>
)
