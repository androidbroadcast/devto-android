package dev.androidbroadcast.devto.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.androidbroadcast.devto.api.entity.TagDto
import dev.androidbroadcast.devto.api.internal.MIMETYPE_JSON
import dev.androidbroadcast.devto.api.internal.authorizedOkHttClient
import dev.androidbroadcast.devto.api.internal.defaultJson
import dev.androidbroadcast.devto.api.internal.retrofit
import dev.androidbroadcast.devto.api.result.Result
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface TagsService {

    /**
     * Retrieve a list of the tags they follow.
     */
    @GET("/follows/tags")
    suspend fun followed(): Result<List<TagDto>>

    /**
     * Retrieve a list of tags that can be used to tag articles.
     * It will return tags ordered by popularity.
     */
    @GET("/tags")
    suspend fun tags(
        @Query("page") @Page page: Int = 1,
        @Query("per_page") @PageSize pageSize: Int = 10
    ): Result<List<TagDto>>
}

@Suppress("unused")
fun TagsService(
    apiKeyProvider: DevtoApiKeyProvider,
    okHttpClient: OkHttpClient = OkHttpClient(),
    json: Json = defaultJson()
): TagsService {
    val retrofit = retrofit(
        okHttpClient.authorizedOkHttClient(apiKeyProvider),
        json.asConverterFactory(MIMETYPE_JSON)
    )
    return retrofit.create()
}
