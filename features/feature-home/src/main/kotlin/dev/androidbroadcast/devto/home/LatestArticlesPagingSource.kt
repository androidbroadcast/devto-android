package dev.androidbroadcast.devto.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import dev.androidbroadcast.devto.api.ArticlesService
import dev.androidbroadcast.devto.api.DevtoApi
import dev.androidbroadcast.devto.api.entity.ArticleDto
import dev.androidbroadcast.devto.api.result.Result
import dev.androidbroadcast.devto.api.result.isFailure
import dev.androidbroadcast.devto.api.result.isSuccess
import dev.androidbroadcast.devto.home.model.Article
import dev.androidbroadcast.devto.home.model.toArticle
import kotlinx.coroutines.delay
import javax.inject.Inject

internal class LatestArticlesPagingSource(
    private val articlesService: ArticlesService
) : PagingSource<Int, Article>() {

    @Inject
    constructor(devtoApi: DevtoApi) : this(devtoApi.articles)

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val page = params.key ?: 1
        val response =
            articlesService.latestArticles(page = page, pageSize = params.loadSize)
        return when {
            response.isSuccess() -> {
                val data = response.value.map { it.toArticle() }
                val nextKey = if (data.isEmpty()) null else page + 1
                val prevKey = if (page > 1) page - 1 else null
                LoadResult.Page(data = data, prevKey = prevKey, nextKey = nextKey)
            }
            response.isFailure() -> {
                LoadResult.Error(response.error ?: Exception("Error loading data"))
            }
            else -> error("Unhandled state")
        }
    }
}
