package dev.androidbroadcast.devto.api

import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient

class DevtoApi(
    private val apiKey: DevtoApiKeyProvider,
    val okHttpClient: OkHttpClient = OkHttpClient()
) {

    private val json = Json {
        ignoreUnknownKeys = true
    }

    val articles by lazy { ArticlesService(apiKey, okHttpClient, json) }
    val comments by lazy { CommentsService(apiKey, okHttpClient, json) }
    val followers by lazy { FollowersService(apiKey, okHttpClient, json) }
    val listings by lazy { ListingsService(apiKey, okHttpClient, json) }
    val organizations by lazy { OrganizationsServices(apiKey, okHttpClient, json) }
    val podcasts by lazy { PodcastsService(apiKey, okHttpClient, json) }
    val profileImages by lazy { ProfileImagesService(apiKey, okHttpClient, json) }
    val tags by lazy { TagsService(apiKey, okHttpClient, json) }
    val users by lazy { UserService(apiKey, okHttpClient, json) }
    val videos by lazy { VideosService(apiKey, okHttpClient, json) }
}
