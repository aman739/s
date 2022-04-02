package com.example.rickandmorty_.models

import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class RickAndMortyResponse<T>(
    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,

    @SerializedName("info")
    val info: Info,

    @SerializedName("results")
    val results: ArrayList<T>
)
