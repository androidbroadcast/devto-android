package dev.androidbroadcast.devto.api.internal

import dev.androidbroadcast.devto.api.result.retrofit.ResultAdapterFactory
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit

internal fun defaultJson() = Json {
    ignoreUnknownKeys = true
}

internal fun retrofit(
    okHttpClient: OkHttpClient,
    vararg factories: Converter.Factory
): Retrofit {
    return Retrofit.Builder().apply {
        baseUrl(DEVTO_API_URL)
        addCallAdapterFactory(ResultAdapterFactory())
        client(okHttpClient)
        factories.forEach(this::addConverterFactory)
    }.build()
}

internal fun OkHttpClient.authorizedOkHttClient(apiKey: String): OkHttpClient {
    return OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(apiKey))
        .build()
}

private const val DEVTO_API_URL = "https://dev.to/api/"
