package dev.androidbroadcast.devto.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.navigationBarsPadding
import com.google.accompanist.insets.rememberInsetsPaddingValues
import dev.androidbroadcast.devto.api.entity.ArticleDto
import dev.androidbroadcast.devto.api.entity.SharedUserDto

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = HomeFeature.featureComponent.homeViewModelFactory)
) {
    val articles: List<ArticleDto> by viewModel.articles.collectAsState(emptyList())
    LazyColumn(
        contentPadding = rememberInsetsPaddingValues(
            insets = LocalWindowInsets.current.systemBars,
            applyTop = false,
            applyBottom = true,
        )
    ) {
        items(articles) { article: ArticleDto ->
            ArticleItem(article, modifier = Modifier.padding(bottom = 4.dp))
        }
    }
}

@Composable
internal fun ArticleItem(article: ArticleDto, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { },
        elevation = 10.dp
    ) {
        Column(modifier) {
            if (article.coverImageUrl != null) {
                Image(
                    painter = rememberImagePainter(data = article.coverImageUrl?.url),
                    contentDescription = null,
                    alignment = Alignment.Center,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(150.dp)
                        .fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.size(8.dp))
            Column(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp)) {
                Creator(creator = article.creator)
                Text(text = article.title, style = MaterialTheme.typography.h5)
                Spacer(modifier = Modifier.size(4.dp))
                if (article.tagList.isNotEmpty()) Tags(article.tagList)
                Row {
                    Icon(Icons.Outlined.Favorite, contentDescription = null)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = article.positiveReactionsCount.toString())

                    Spacer(modifier = Modifier.size(8.dp))

                    Icon(Icons.Filled.Face, contentDescription = null)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = article.commentsCount.toString())
                }
            }
        }
    }
}

@Composable
private fun Tags(tags: List<String>, modifier: Modifier = Modifier) {
    Row(modifier) {
        tags.forEach { tag ->
            Text(
                text = "#${tag}",
                modifier = Modifier
                    .padding(all = 4.dp)
                    .background(MaterialTheme.colors.onSurface.copy(alpha = 0.1F))
            )
            Spacer(modifier = Modifier.size(4.dp))
        }
    }
}

@Composable
internal fun Creator(creator: SharedUserDto, modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        Image(
            painter = rememberImagePainter(
                data = creator.profileImage?.url,
                builder = {
                    transformations(CircleCropTransformation())
                }
            ),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(32.dp)
        )
        Spacer(modifier = Modifier.size(4.dp))
        Column(verticalArrangement = Arrangement.Center) {
            Text(text = creator.name, style = MaterialTheme.typography.body2, maxLines = 1)
            Text(
                text = "@${creator.username}",
                style = MaterialTheme.typography.caption,
                maxLines = 1
            )
        }
    }
}
