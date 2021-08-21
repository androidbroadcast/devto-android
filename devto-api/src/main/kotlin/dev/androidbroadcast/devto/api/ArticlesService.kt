package dev.androidbroadcast.devto.api

import androidx.annotation.IntRange
import dev.androidbroadcast.devto.api.entity.Article
import dev.androidbroadcast.devto.api.entity.State
import dev.androidbroadcast.devto.api.result.Result
import dev.androidbroadcast.devto.api.result.Video
import dev.androidbroadcast.devto.api.result.retrofit.ResultAdapterFactory
import dev.androidbroadcast.devto.api.result.retrofit.ResultCall
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesService {

    @GET("articles")
    suspend fun articles(
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("tags") tags: String? = null,
        @Query("tags_exclude") excludeTags: String? = null,
        @Query("state") state: State = State.FRESH,
        @Query("collection_id") collectionId: Int? = null
    ): Result<List<Article>>

    @GET("articles")
    suspend fun articles(
        @Query("username") username: String,
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("tags") tags: String? = null,
        @Query("tags_exclude") excludeTags: String? = null,
        @Query("collection_id") collectionId: Int? = null
    ): Result<List<Article>>

    @GET("articles")
    suspend fun articles(
        @Query("top") @IntRange(from = 1) top: Int,
        @Query("page") @Page page: Int,
        @Query("per_page") @PageSize pageSize: Int,
        @Query("tag") tag: String? = null,
        @Query("state") state: State = State.FRESH,
        @Query("collection_id") collectionId: Int? = null
    ): Result<List<Article>>

    /**
     * Return published articles sorted by publish date
     */
    @GET("articles/latest")
    suspend fun latestArticles(
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Result<List<Article>>

    @GET("articles/{id}")
    suspend fun article(@Path("id") @ArticleId id: Int): Result<Article>

    @GET("articles/{username}/{slug}")
    suspend fun articles(
        @Path("username") username: String,
        @Path("slug") slug: String,
    ): Result<List<Article>>

    @GET("articles/me")
    suspend fun userArticles(
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Result<List<Article>>

    @GET("articles/me/published")
    suspend fun userPublishedArticles(
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE
    ): Result<List<Article>>

    @GET("articles/me/unpublished")
    suspend fun userUnpublishedArticles(
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Result<List<Article>>

    @GET("articles/me/unpublished")
    suspend fun userAllArticles(
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Result<List<Article>>

    @GET("videos")
    suspend fun videos(
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Result<List<Video>>

    companion object {

        @Suppress("unused")
        const val DEFAULT_PAGE = 1
        const val DEFAULT_PAGE_SIZE = 30
    }
}

@IntRange(from = 1, to = 1000)
private annotation class PageSize

@IntRange(from = 1)
private annotation class Page

@IntRange(from = 1)
private annotation class ArticleId

@Suppress("unused")
fun ArticlesService(apiKey: String): ArticlesService {
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor(apiKey))
        .build()

    val retrofit = Retrofit.Builder()
        .baseUrl(DEVTO_API_URL)
        .addCallAdapterFactory(ResultAdapterFactory())
        .client(okHttpClient)
        .build()

    return retrofit.create()
}

private const val DEVTO_API_URL = "https://dev.to/api"

private class AuthInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(HEADER_API_KEY, apiKey)
            .build()

        return chain.proceed(request)
    }

    private companion object {

        private const val HEADER_API_KEY = "api-key"
    }
}