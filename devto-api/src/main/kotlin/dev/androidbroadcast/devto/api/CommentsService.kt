package dev.androidbroadcast.devto.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.androidbroadcast.devto.api.entity.Comment
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
    fun articleComments(@Query("a_id") id: String): Result<List<Comment>>

    @GET("/comments")
    fun podcastComments(@Query("p_id") id: String): Result<List<Comment>>

    @GET("/comments/{id}")
    fun comments(@Path("id") id: String): Result<Comment>
}

@Suppress("unused")
fun CommentsService(
    apiKey: String,
    okHttpClient: OkHttpClient = OkHttpClient(),
    json: Json = defaultJson()
): CommentsService {
    val retrofit = retrofit(
        okHttpClient.authorizedOkHttClient(apiKey),
        json.asConverterFactory(MIMETYPE_JSON)
    )
    return retrofit.create()
}
