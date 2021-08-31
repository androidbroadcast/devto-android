package dev.androidbroadcast.devto.home.model

import android.annotation.SuppressLint
import dev.androidbroadcast.devto.api.entity.ArticleDto
import dev.androidbroadcast.devto.api.entity.SharedUserDto
import java.text.DateFormat
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
internal fun ArticleDto.toArticle(): Article {
    val df1: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
    return Article(
        id = id,
        title = title,
        imageUrl = coverImageUrl?.url,
        readablePublishDate = readablePublishDate,
        tags = tagList,
        commentsCount = commentsCount,
        positiveReactionsCount = positiveReactionsCount,
        publishedAt = requireNotNull(df1.parse(publishedAt)),
        creator = creator.toCreator(),
        readingTimeMinutes = readingTimeMinutes
    )
}

internal fun SharedUserDto.toCreator(): Article.User {
    return Article.User(name, username, profileImage?.url)
}
