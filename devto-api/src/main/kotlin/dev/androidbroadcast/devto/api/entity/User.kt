package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("type_of") val typeOf: String,
    @SerialName("id") val id: Int,
    @SerialName("username") val username: String,
    @SerialName("name") val name: String,
    @SerialName("summary") val summary: String?,
    @SerialName("twitter_username") val twitterUsername: String?,
    @SerialName("github_username") val githubUsername: String?,
    @SerialName("website_url") val websiteUrl: String?,
    @SerialName("location") val location: String?,
    @SerialName("joined_at") val joinedAt: String,
    @SerialName("profile_image") val profileImageUrl: String,
)