package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProfileImage(
    @SerialName("type_of") val typeOf: String,
    @SerialName("image_of") val imageOf: ImageType,
    @SerialName("profile_image") val profileImageUrl: Url,
    @SerialName("profile_image_90") val profileImageUrl90: Url,
) {

    @Serializable
    enum class ImageType {
        @SerialName("user")
        USER,

        @SerialName("organization")
        ORGANIZATION
    }
}