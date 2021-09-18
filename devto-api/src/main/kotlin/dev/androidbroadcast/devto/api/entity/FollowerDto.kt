package dev.androidbroadcast.devto.api.entity

import dev.androidbroadcast.devto.api.internal.IsoDateSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.Serializer
import java.util.Date

@Serializable
data class FollowerDto(
    @SerialName("type_of") val typeOf: String,
    @SerialName("created_at") @Serializable(IsoDateSerializer::class) val createdAt: Date,
    @SerialName("id") val id: Int,
    @SerialName("name") val name: String,
    @SerialName("path") val path: String,
    @SerialName("username") val username: String,
    /**
     * Profile image (60x60)
     */
    @SerialName("profile_image") val profileImageUrl: String,
)
