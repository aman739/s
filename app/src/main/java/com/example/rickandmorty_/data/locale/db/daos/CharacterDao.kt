package com.example.rickandmorty_.data.locale.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty_.models.RickAndMortyCharacter

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: RickAndMortyCharacter)

    @Query("SELECT * FROM rickandmortycharacter")
    suspend fun getAll(): List<RickAndMortyCharacter>
}