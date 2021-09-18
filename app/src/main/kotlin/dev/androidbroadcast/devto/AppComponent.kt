package dev.androidbroadcast.devto

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.androidbroadcast.devto.api.DevtoApi
import dev.androidbroadcast.devto.api.DevtoApiKeyProvider
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Singleton
    @Provides
    fun providerDevApi(apiKeyProvider: DevtoApiKeyProvider) = DevtoApi(apiKeyProvider)

    @Provides
    fun providerDevApiKeyProvider() = object : DevtoApiKeyProvider {

        override val apiKey: String = BuildConfig.DEVTO_API_KEY
    }
}
