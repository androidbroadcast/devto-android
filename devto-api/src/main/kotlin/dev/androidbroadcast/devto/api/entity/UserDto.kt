package dev.androidbroadcast.devto.api.entity

import dev.androidbroadcast.devto.api.internal.IsoDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import java.util.Date

@Serializable
data class UserDto(
    @SerialName("type_of") val typeOf: String,
    @SerialName("id") val id: Int,
    @SerialName("username") val username: String,
    @SerialName("name") val name: String,
    @SerialName("summary") val summary: String? = null,
    @SerialName("twitter_username") val twitterUsername: String? = null,
    @SerialName("github_username") val githubUsername: String? = null,
    @SerialName("website_url") val websiteUrl: String? = null,
    @SerialName("location") val location: String? = null,
    @SerialName("joined_at") @Serializable(IsoDateSerializer::class) val joinedAt: Date,
    @SerialName("profile_image") val profileImageUrl: String,
)
