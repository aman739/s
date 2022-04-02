package com.example.rickandmorty_.data.locale.db

import android.content.Context
import androidx.room.Room
import com.example.rickandmorty_.data.locale.db.daos.CharacterDao
import com.example.rickandmorty_.data.locale.db.daos.EpisodesDao
import com.example.rickandmorty_.data.locale.db.daos.LocationDao

class RoomClient {
    fun provideCrateAppDatabase(context: Context) = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "database.rickandmorty.com"
    ).fallbackToDestructiveMigration()
        .build()

    fun provideCharacterDao(appDatabase: AppDatabase): CharacterDao = appDatabase.characterDao()

    fun provideEpisodesDao(appDatabase: AppDatabase): EpisodesDao = appDatabase.episodesDao()

    fun provideLocationDao(appDatabase: AppDatabase): LocationDao = appDatabase.locationDao()
}