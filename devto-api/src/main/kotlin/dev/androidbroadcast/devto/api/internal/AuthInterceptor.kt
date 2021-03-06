package dev.androidbroadcast.devto.api.internal

import dev.androidbroadcast.devto.api.DevtoApiKeyProvider
import dev.androidbroadcast.devto.api.requireApiKey
import okhttp3.Interceptor
import okhttp3.Response

internal class AuthInterceptor(private val apiKeyProvider: DevtoApiKeyProvider) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(HEADER_API_KEY, apiKeyProvider.requireApiKey())
            .build()

        return chain.proceed(request)
    }

    private companion object {

        private const val HEADER_API_KEY = "api-key"
    }
}
