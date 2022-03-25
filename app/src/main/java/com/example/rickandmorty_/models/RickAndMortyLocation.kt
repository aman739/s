package com.example.rickandmorty_.models

import com.google.gson.annotations.SerializedName

data class RickAndMortyLocation(

    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("dimension")
    val dimension: String,
)
