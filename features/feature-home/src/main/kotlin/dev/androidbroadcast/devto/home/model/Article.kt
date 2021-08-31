package dev.androidbroadcast.devto.home.model

import java.util.Date

internal class Article(
    val id: Int,
    val title: String,
    val imageUrl: String?,
    val readablePublishDate: String,
    val tags: List<String>,
    val positiveReactionsCount: Int,
    val commentsCount: Int,
    val publishedAt: Date,
    val creator: User,
    val readingTimeMinutes: Int
) {

    class User(
        val name: String,
        val username: String,
        val profileImageUrl: String?,
    )
}
