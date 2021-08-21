package dev.androidbroadcast.devto.api

import kotlinx.serialization.json.Json

internal fun defaultJson() = Json {
    ignoreUnknownKeys = true
}