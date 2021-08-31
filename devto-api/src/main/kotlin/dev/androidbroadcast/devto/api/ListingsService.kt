package dev.androidbroadcast.devto.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dev.androidbroadcast.devto.api.entity.Listing
import dev.androidbroadcast.devto.api.internal.MIMETYPE_JSON
import dev.androidbroadcast.devto.api.internal.authorizedOkHttClient
import dev.androidbroadcast.devto.api.internal.defaultJson
import dev.androidbroadcast.devto.api.internal.retrofit
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient
import retrofit2.create
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface ListingsService {

    @GET("/listings/{id}")
    suspend fun listing(@Path("id") id: Long): Result<Listing>

    @GET("/listings")
    suspend fun listings(
        @Query("page") @Page page: Int = 1,
        @Query("per_page") @PageSize pageSize: Int = 30
    ): Result<List<Listing>>

    @POST("/listings")
    suspend fun newListing(@Body listing: Listing): Result<Listing>

    @GET("/listings/category/{category}")
    suspend fun listings(
        @Path("category") category: Listing.Category,
        @Query("page") @Page page: Int = 1,
        @Query("per_page") @PageSize pageSize: Int = 30
    ): Result<List<Listing>>

    /**
     * Update an existing listing.
     *
     *  Note: except for bumping, publishing and unpublishing there are the following restrictions
     *  on the ability to update listings:
     *  - the payload has to contain at least one param among title, body_markdown or tags/tag_list
     *  - the listing can't be updated if it has not been bumped in the last 24 hours
     *  - the listing can't be updated if it has been published but not recently bumped
     *
     *
     * @param listing Listing params for the update.
     */
    @PUT("/listings/{id}")
    suspend fun updateListing(
        @Path("id") id: Long,
        @Body listing: Listing
    ): Result<Listing>
}

@Suppress("unused")
fun ListingsService(
    apiKey: String,
    okHttpClient: OkHttpClient = OkHttpClient(),
    json: Json = defaultJson()
): ListingsService {
    val retrofit = retrofit(
        okHttpClient.authorizedOkHttClient(apiKey),
        json.asConverterFactory(MIMETYPE_JSON)
    )
    return retrofit.create()
}
