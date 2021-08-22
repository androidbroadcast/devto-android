package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Follower(
    @SerialName("type_of") val typeOf: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("path") val path: String,
    @SerialName("username") val username: String,
    /**
     * Profile image (60x60)
     */
    @SerialName("profile_image") val profileImageUrl: String,
)