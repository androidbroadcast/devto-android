package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Comment(
    @SerialName("type_of") val typeOf: String,
    @SerialName("id_code") val idCode: String,
    @SerialName("created_at") val createdAt: String,
    @SerialName("body_html") val bodyHtml: String,
    @SerialName("user") val user: SharedUser,
    @SerialName("children") val children: List<Comment>
)
