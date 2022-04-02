package com.example.rickandmorty_.data.locale.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.rickandmorty_.data.locale.db.daos.CharacterDao
import com.example.rickandmorty_.data.locale.db.daos.EpisodesDao
import com.example.rickandmorty_.data.locale.db.daos.LocationDao
import com.example.rickandmorty_.models.RickAndMortyCharacter
import com.example.rickandmorty_.models.RickAndMortyEpisodes
import com.example.rickandmorty_.models.RickAndMortyLocation

@Database(
    entities = [RickAndMortyCharacter::class,
        RickAndMortyEpisodes::class,
        RickAndMortyLocation::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun characterDao(): CharacterDao
    abstract fun episodesDao(): EpisodesDao
    abstract fun locationDao(): LocationDao
}