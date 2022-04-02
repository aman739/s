package com.example.rickandmorty_.data.locale.db.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.rickandmorty_.models.RickAndMortyLocation

@Dao
interface LocationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg users: RickAndMortyLocation)

    @Query("SELECT * FROM rickandmortylocation")
    suspend fun getAll(): List<RickAndMortyLocation>
}