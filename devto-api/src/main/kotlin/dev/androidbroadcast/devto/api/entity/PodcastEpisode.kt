package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PodcastEpisode(
    @SerialName("type_of") val typeOf: String,
    @SerialName("id") val id: Int,
    @SerialName("path") val path: String,
    @SerialName("image_url") val imageUrl: Url,
    @SerialName("title") val title: String,
    @SerialName("podcast") val podcast: Podcast
) {

    @Serializable
    data class Podcast(
        @SerialName("title") val title: String,
        @SerialName("slug") val slug: String,
        @SerialName("image_url") val imageUrl: Url
    )
}