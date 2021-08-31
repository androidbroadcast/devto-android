package dev.androidbroadcast.devto.api.entity

import kotlinx.serialization.Serializable

@Serializable
data class TagDto(
    val id: Long,
    val name: String,
    val points: List<Float>,
)
