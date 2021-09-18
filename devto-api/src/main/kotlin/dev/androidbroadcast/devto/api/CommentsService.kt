package dev.androidbroadcast.devto.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.androidbroadcast.devto.api.entity.CommentDto
import dev.androidbroadcast.devto.api.internal.MIMETYPE_JSON
import dev.androidbroadcast.devto.api.internal.authorizedOkHttClient
import dev.androidbroadcast.devto.api.internal.defaultJson
import dev.androidbroadcast.devto.api.internal.retrofit
import dev.androidbroadcast.devto.api.result.Result
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentsService {

    @GET("/comments")
    fun articleComments(@Query("a_id") id: String): Result<List<CommentDto>>

    @GET("/comments")
    fun podcastComments(@Query("p_id") id: String): Result<List<CommentDto>>

    @GET("/comments/{id}")
    fun comments(@Path("id") id: String): Result<CommentDto>
}

@Suppress("unused")
fun CommentsService(
    apiKeyProvider: DevtoApiKeyProvider,
    okHttpClient: OkHttpClient = OkHttpClient(),
    json: Json = defaultJson()
): CommentsService {
    val retrofit = retrofit(
        okHttpClient.authorizedOkHttClient(apiKeyProvider),
        json.asConverterFactory(MIMETYPE_JSON)
    )
    return retrofit.create()
}
