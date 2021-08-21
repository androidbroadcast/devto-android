package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    @SerialName("type_of") val typeOf: String,
    val id: Int,
    /**
     * The preview image of the video
     */
    @SerialName("cloudinary_video_url") val cloudinaryVideoUrl: Url,
    val title: String,
    @SerialName("user_id") val userId: Int,
    @SerialName("video_duration_in_minutes") val durationMinutes: Int,
    @SerialName("video_source_url") val sourceUrl: Url,
    val user: User,
)