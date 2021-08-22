package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Article(
    @SerialName("type_of") val typeOf: String,
    val id: Int,
    val title: String,
    val description: String,
    @SerialName("cover_image") val coverImageUrl: Url?,
    @SerialName("readable_publish_date") val readablePublishDate: String,
    @SerialName("social_image") val socialImageUrl: String,
    @SerialName("tag_list") val tagList: List<String>,
    @SerialName("tags") val tags: String,
    @SerialName("path") val path: ArticlePath,
    @SerialName("url") val url: Url,
    @SerialName("canonical_url") val canonicalUrl: Url,
    @SerialName("comments_count") val commentsCount: Int,
    @SerialName("positive_reactions_count") val positiveReactionsCount: Int,
    @SerialName("public_reactions_count") val publicReactionsCount: Int,
    @SerialName("created_at") val createdAt: String,
    @SerialName("edited_at") val editedAt: String,
    @SerialName("crossposted_at") val crosspostedAt: String,
    @SerialName("published_at") val publishedAt: String,
    @SerialName("last_comment_at") val lastCommentAt: String,
    /**
     * [Article.crosspostedAt] or [Article.publishedAt]
     */
    @SerialName("publishedTimestamp") val publishedTimestamp: String,
    @SerialName("user") val creator: SharedUser,
    @SerialName("reading_time_minutes") val readingTimeMinutes: Int,
    /**
     * The organization the resource belongs to
     */
    val organization: SharedOrganization,
    @SerialName("flare_tag") val flareTag: ArticleFlareTag

)
