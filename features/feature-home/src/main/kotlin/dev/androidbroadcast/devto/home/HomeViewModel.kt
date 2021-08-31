package dev.androidbroadcast.devto.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.androidbroadcast.devto.api.entity.ArticleDto
import dev.androidbroadcast.devto.api.result.Result
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Provider

class HomeViewModel @Inject internal constructor(
    private val homeArticlesUseCase: HomeArticlesUseCase
) : ViewModel() {

    val articles = flow {
        val result: Result<List<ArticleDto>> =
            homeArticlesUseCase.invoke()
        if (result is Result.Success<List<ArticleDto>>) {
            emit(result.value)
        } else if (result is Result.Failure<*>) {
            error(result.error?.message ?: "Error")
        }
    }

    internal class Factory @Inject constructor(
        private val homeArticlesUseCase: Provider<HomeArticlesUseCase>
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == HomeViewModel::class.java)
            return HomeViewModel(homeArticlesUseCase.get()) as T
        }
    }
}
