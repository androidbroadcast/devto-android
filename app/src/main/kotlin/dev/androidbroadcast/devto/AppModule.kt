package dev.androidbroadcast.devto

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.androidbroadcast.devto.api.DevtoApi
import dev.androidbroadcast.devto.api.DevtoApiKeyProvider
import okhttp3.OkHttpClient

@[Module InstallIn(SingletonComponent::class)]
class AppModule {

    @Provides
    fun providerDevApi(
        apiKeyProvider: DevtoApiKeyProvider,
        okHttpClient: OkHttpClient
    ): DevtoApi {
        return DevtoApi(apiKeyProvider, okHttpClient)
    }

    @Provides
    fun providerDevApiKeyProvider() = object : DevtoApiKeyProvider {

        override val apiKey: String = BuildConfig.DEVTO_API_KEY
    }
}
