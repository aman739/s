package com.example.rickandmorty_.di

import android.content.Context
import com.example.rickandmorty_.data.locale.db.AppDatabase
import com.example.rickandmorty_.data.locale.db.RoomClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppDatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context:Context)=
        RoomClient().provideCrateAppDatabase(context)

    @Singleton
    @Provides
    fun provideCharacterDao(appDatabase: AppDatabase)=
        RoomClient().provideCharacterDao(appDatabase)

    @Singleton
    @Provides
    fun provideEpisodesDao(appDatabase: AppDatabase)=
        RoomClient().provideEpisodesDao(appDatabase)

    @Singleton
    @Provides
    fun provideLocationDao(appDatabase: AppDatabase)=
        RoomClient().provideLocationDao(appDatabase)

}