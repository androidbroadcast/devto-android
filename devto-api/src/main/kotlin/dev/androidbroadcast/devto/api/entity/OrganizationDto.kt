package dev.androidbroadcast.devto.api.entity

import dev.androidbroadcast.devto.api.internal.IsoDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import java.util.Date

@Serializable
data class OrganizationDto(
    val typeOf: String,
    @SerialName("username") val username: String,
    @SerialName("name") val name: String,
    @SerialName("summary") val summary: String? = null,
    @SerialName("twitter_username") val twitterUsername: String? = null,
    @SerialName("github_username") val githubUsername: String? = null,
    @SerialName("url") val url: String? = null,
    @SerialName("location") val location: String? = null,
    @SerialName("tech_stack") val techStack: String? = null,
    @SerialName("tag_line") val tagLine: String? = null,
    @SerialName("story") val story: String? = null,
    @SerialName("joined_at") @Serializable(IsoDateSerializer::class) val joinedAt: Date? = null,
    /**
     * Profile image (640x640)
     */
    @SerialName("profile_image") val profileImageUrl: UrlDto? = null,
)
