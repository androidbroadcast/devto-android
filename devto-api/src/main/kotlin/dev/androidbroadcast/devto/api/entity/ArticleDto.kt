package dev.androidbroadcast.devto.api.entity

import dev.androidbroadcast.devto.api.internal.IsoDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class ArticleDto(
    @SerialName("type_of") val typeOf: String,
    val id: Int,
    val title: String,
    val description: String,
    @SerialName("cover_image") val coverImageUrl: UrlDto?,
    @SerialName("readable_publish_date") val readablePublish: String,
    @SerialName("social_image") val socialImageUrl: String,
    @SerialName("tag_list") val tagList: List<String>,
    @SerialName("tags") val tags: String,
    @SerialName("path") val path: ArticlePathDto,
    @SerialName("url") val url: UrlDto,
    @SerialName("canonical_url") val canonicalUrl: UrlDto,
    @SerialName("comments_count") val commentsCount: Int,
    @SerialName("positive_reactions_count") val positiveReactionsCount: Int,
    @SerialName("public_reactions_count") val publicReactionsCount: Int,
    @SerialName("created_at") @Serializable(IsoDateSerializer::class)
    val createdAt: Date,
    @SerialName("edited_at") @Serializable(IsoDateSerializer::class)
    val editedAt: Date?,
    @SerialName("crossposted_at") @Serializable(IsoDateSerializer::class)
    val crosspostedAt: Date?,
    @SerialName("published_at") @Serializable(IsoDateSerializer::class)
    val publishedAt: Date,
    @SerialName("last_comment_at") @Serializable(IsoDateSerializer::class)
    val lastCommentAt: Date,
    /**
     * [ArticleDto.crosspostedAt] or [ArticleDto.publishedAt]
     */
    @SerialName("published_timestamp") @Serializable(IsoDateSerializer::class)
    val publishedTimestamp: Date? = null,
    @SerialName("user") val creator: SharedUserDto,
    @SerialName("reading_time_minutes") val readingTimeMinutes: Int,
    /**
     * The organization the resource belongs to
     */
    @SerialName("organization") val organization: SharedOrganizationDto? = null,
    @SerialName("flare_tag") val flareTag: ArticleFlareTagDto? = null,
    @SerialName("slug") val slug: String? = null,
    @SerialName("collection_id") val collectionId: Long? = null,
)
