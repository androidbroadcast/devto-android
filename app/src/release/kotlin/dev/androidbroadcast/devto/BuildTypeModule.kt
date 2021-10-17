package dev.androidbroadcast.devto

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@[Module InstallIn(SingletonComponent::class)]
class BuildTypeModule {

   @[Provides Singleton]
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient()
    }
}
