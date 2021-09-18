package dev.androidbroadcast.devto.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.Lazy
import dev.androidbroadcast.devto.home.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class HomeViewModel @Inject internal constructor(
    private val latestArticlesPagingSource: Lazy<LatestArticlesPagingSource>
) : ViewModel() {

    internal val articles: Flow<PagingData<Article>> = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        latestArticlesPagingSource.get()
    }.flow
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    internal class Factory @Inject constructor(
        private val latestArticlesPagingSource: Lazy<LatestArticlesPagingSource>
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            require(modelClass == HomeViewModel::class.java)
            return HomeViewModel(latestArticlesPagingSource) as T
        }
    }
}
