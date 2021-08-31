package dev.androidbroadcast.devto.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.androidbroadcast.devto.api.entity.Article
import dev.androidbroadcast.devto.api.entity.Listing
import dev.androidbroadcast.devto.api.entity.Organization
import dev.androidbroadcast.devto.api.entity.User
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

interface OrganizationsServices {

    @GET("/organizations/{username}")
    suspend fun organization(
        @Path("username") organizationUsername: String,
    ): Result<Organization>

    @GET("/organizations/{username}/users")
    suspend fun users(
        @Path("username") organizationUsername: String,
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Result<List<User>>

    @GET("/organizations/{username}/listings")
    suspend fun listings(
        @Path("username") organizationUsername: String,
        @Query("category") category: String? = null,
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Result<List<Listing>>

    @GET("/organizations/{username}/articles")
    suspend fun articles(
        @Path("username") organizationUsername: String,
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Result<List<Article>>

    companion object {
        const val DEFAULT_PAGE = 1
        const val DEFAULT_PAGE_SIZE = 30
    }
}

@Suppress("FunctionName")
fun OrganizationsServices(
    apiKey: String,
    okHttpClient: OkHttpClient = OkHttpClient(),
    json: Json = defaultJson()
): OrganizationsServices {
    val retrofit = retrofit(
        okHttpClient.authorizedOkHttClient(apiKey),
        json.asConverterFactory(MIMETYPE_JSON)
    )
    return retrofit.create()
}
