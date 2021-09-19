package dev.androidbroadcast.devto.user.model

import java.util.Date

internal data class User(
    val id: Int,
    val username: String,
    val name: String,
    val summary: String?,
    val twitterUsername: String?,
    val githubUsername: String?,
    val websiteUrl: String?,
    val location: String?,
    val joinedAt: Date,
    val profileImageUrl: String,
) {
}
