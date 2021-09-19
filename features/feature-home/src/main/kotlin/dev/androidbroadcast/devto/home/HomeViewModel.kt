package dev.androidbroadcast.devto.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import dagger.Lazy
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.androidbroadcast.devto.home.model.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject internal constructor(
    private val latestArticlesPagingSource: Lazy<LatestArticlesPagingSource>
) : ViewModel() {

    internal val articles: Flow<PagingData<Article>> =
        Pager(PagingConfig(pageSize = 20)) { latestArticlesPagingSource.get() }
        .flow
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())
}
