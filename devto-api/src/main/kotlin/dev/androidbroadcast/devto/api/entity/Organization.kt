package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Organization(
    val typeOf: String,
    @SerialName("username") val username: String,
    @SerialName("name") val name: String,
    @SerialName("summary") val summary: String?,
    @SerialName("twitter_username") val twitterUsername: String?,
    @SerialName("github_username") val githubUsername: String?,
    @SerialName("url") val url: String?,
    @SerialName("location") val location: String?,
    @SerialName("tech_stack") val techStack: String?,
    @SerialName("tag_line") val tagLine: String?,
    @SerialName("story") val story: String?,
    @SerialName("joined_at") val joinedAt: String?,
    /**
     * Profile image (640x640)
     */
    @SerialName("profile_image") val profileImage: Url?,
)
