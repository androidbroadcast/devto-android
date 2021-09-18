package dev.androidbroadcast.devto.api

interface DevtoApiKeyProvider {

    val apiKey: String?
}

fun DevtoApiKeyProvider.requireApiKey(): String {
    return checkNotNull(apiKey)
}
