package com.example.rickandmorty_.di

import com.example.rickandmorty_.data.remote.RetrofitClient
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Singleton
    val retrofitClient = RetrofitClient()

    @Singleton
    @Provides
    fun providerCharacterApiService() = retrofitClient.provideCharacterApiService()

    @Singleton
    @Provides
    fun providerEpisodesApiService() = retrofitClient.providerEpisodesApiService()

    @Singleton
    @Provides
    fun providerLocationsApiService() = retrofitClient.providerLocationsApiService()
}