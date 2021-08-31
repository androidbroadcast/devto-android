package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleFlareTagDto(
    val name: String,
    @SerialName("bg_color_hex") val bgColor: ColorDto,
    @SerialName("text_color_hex") val textColor: ColorDto,
)
