package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleDto(
    @SerialName("type_of") val typeOf: String,
    val id: Int,
    val title: String,
    val description: String,
    @SerialName("cover_image") val coverImageUrl: UrlDto?,
    @SerialName("readable_publish_date") val readablePublishDate: String,
    @SerialName("social_image") val socialImageUrl: String,
    @SerialName("tag_list") val tagList: List<String>,
    @SerialName("tags") val tags: String,
    @SerialName("path") val path: ArticlePathDto,
    @SerialName("url") val url: UrlDto,
    @SerialName("canonical_url") val canonicalUrl: UrlDto,
    @SerialName("comments_count") val commentsCount: Int,
    @SerialName("positive_reactions_count") val positiveReactionsCount: Int,
    @SerialName("public_reactions_count") val publicReactionsCount: Int,
    @SerialName("created_at") val createdAt: String,
    @SerialName("edited_at") val editedAt: String?,
    @SerialName("crossposted_at") val crosspostedAt: String?,
    @SerialName("published_at") val publishedAt: String,
    @SerialName("last_comment_at") val lastCommentAt: String,
    /**
     * [ArticleDto.crosspostedAt] or [ArticleDto.publishedAt]
     */
    @SerialName("published_timestamp") val publishedTimestamp: String? = null,
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
