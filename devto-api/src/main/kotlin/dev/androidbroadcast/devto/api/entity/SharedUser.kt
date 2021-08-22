package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SharedUser(
    val name: String,
    val username: String,
    @SerialName("twitter_username") val twitterUsername: String?,
    @SerialName("website_url") val website: Url?,
    /**
     * Profile image (640x640)
     */
    @SerialName("profile_image") val profileImage: Url?,
    /**
     * Profile image (90x90)
     */
    @SerialName("profile_image_90") val profileImageSmall: Url?,
)