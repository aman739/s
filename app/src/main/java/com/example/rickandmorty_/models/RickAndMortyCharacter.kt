package com.example.rickandmorty_.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class RickAndMortyCharacter(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("image")
    val image: String,
)
