package dev.androidbroadcast.devto.home

import dev.androidbroadcast.devto.api.DevtoApi
import dev.androidbroadcast.devto.api.entity.ArticleDto
import dev.androidbroadcast.devto.api.result.Result
import dev.androidbroadcast.devto.api.result.map
import dev.androidbroadcast.devto.home.model.Article
import dev.androidbroadcast.devto.home.model.toArticle
import javax.inject.Inject

internal class HomeArticlesUseCase @Inject constructor(private val devtoApi: DevtoApi) {

    suspend operator fun invoke(): Result<List<Article>> {
        return devtoApi.articles.latestArticles().map { articleDtos ->
            articleDtos.map(ArticleDto::toArticle)
        }
    }
}
