package dev.androidbroadcast.devto.api.result

interface HttpResponse {

    val statusCode: Int

    val statusMessage: String?

    val url: String?
}
