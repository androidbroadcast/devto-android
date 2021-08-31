package dev.androidbroadcast.devto.api

import androidx.annotation.IntRange
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.androidbroadcast.devto.api.entity.Article
import dev.androidbroadcast.devto.api.entity.State
import dev.androidbroadcast.devto.api.result.Result
import dev.androidbroadcast.devto.api.internal.MIMETYPE_JSON
import dev.androidbroadcast.devto.api.internal.authorizedOkHttClient
import dev.androidbroadcast.devto.api.internal.defaultJson
import dev.androidbroadcast.devto.api.internal.retrofit
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

@Suppress("TooManyFunctions")
interface ArticlesService {

    @GET("articles")
    @Suppress("LongParameterList")
    suspend fun articles(
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("tags") tags: String? = null,
        @Query("tags_exclude") excludeTags: String? = null,
        @Query("state") state: State = State.FRESH,
        @Query("collection_id") collectionId: Int? = null
    ): Result<List<Article>>

    @GET("articles")
    @Suppress("LongParameterList")
    suspend fun articles(
        @Query("username") username: String,
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("tags") tags: String? = null,
        @Query("tags_exclude") excludeTags: String? = null,
        @Query("collection_id") collectionId: Int? = null
    ): Result<List<Article>>

    @GET("articles")
    @Suppress("LongParameterList")
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

    @GET("readinglist")
    suspend fun readingList(
        @Query("page") @Page page: Int = DEFAULT_PAGE,
        @Query("per_page") @PageSize pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Result<List<Article>>

    @PUT("/articles/{id}")
    suspend fun updateArticle(
        @ArticleId @Path("id") id: Int,
        @Body article: Article,
    ): Result<Article>

    companion object {
        const val DEFAULT_PAGE = 1
        const val DEFAULT_PAGE_SIZE = 30
    }
}

@IntRange(from = 1, to = 1000)
internal annotation class PageSize

@IntRange(from = 1)
internal annotation class Page

@IntRange(from = 1)
internal annotation class ArticleId

@Suppress("unused")
fun ArticlesService(
    apiKey: String,
    okHttpClient: OkHttpClient = OkHttpClient(),
    json: Json = defaultJson()
): ArticlesService {
    val retrofit = retrofit(
        okHttpClient.authorizedOkHttClient(apiKey),
        json.asConverterFactory(MIMETYPE_JSON)
    )
    return retrofit.create()
}
