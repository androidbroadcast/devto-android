package dev.androidbroadcast.devto.home

import dev.androidbroadcast.devto.api.DevtoApi
import dev.androidbroadcast.devto.api.entity.ArticleDto
import dev.androidbroadcast.devto.api.result.Result
import javax.inject.Inject

internal class HomeArticlesUseCase @Inject constructor(private val devtoApi: DevtoApi) {

    suspend operator fun invoke(): Result<List<ArticleDto>> {
        return devtoApi.articles.latestArticles()
    }
}
