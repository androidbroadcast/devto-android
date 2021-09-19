package dev.androidbroadcast.devto.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.androidbroadcast.devto.api.entity.UserDto
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

interface UserService {

    @GET("users/{id}")
    suspend fun userById(@Path("id") id: Int): Result<UserDto>

    @GET("users/by_username")
    suspend fun userBeUrl(@Query("url") url: String): Result<UserDto>

    @GET("users/me")
    suspend fun currentUser(): Result<UserDto>
}

@Suppress("FunctionName")
fun UserService(
    apiKeyProvider: DevtoApiKeyProvider,
    okHttpClient: OkHttpClient = OkHttpClient(),
    json: Json = defaultJson()
): UserService {
    val retrofit = retrofit(
        okHttpClient.authorizedOkHttClient(apiKeyProvider),
        json.asConverterFactory(MIMETYPE_JSON)
    )
    return retrofit.create()
}
