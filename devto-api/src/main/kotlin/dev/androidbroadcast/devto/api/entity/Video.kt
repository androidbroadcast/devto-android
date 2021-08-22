package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Video(
    @SerialName("type_of") val typeOf: String,
    @SerialName("id") val id: Int,
    @SerialName("path") val path: String,
    /**
     * The preview image of the video
     */
    @SerialName("cloudinary_video_url") val cloudinaryVideoUrl: Url,
    @SerialName("title") val title: String,
    @SerialName("user_id") val userId: Int,
    /**
     * If the video duration is below 1 hour,
     * the format will be mm:ss, if it's 1 hour or above the format will be h:mm:ss.
     */
    @SerialName("video_duration_in_minutes") val durationMinutes: String,
    @SerialName("video_source_url") val sourceUrl: Url,
    @SerialName("user") val user: User,
) {

    @Serializable
    class User(@SerialName("name") val name: String)
}