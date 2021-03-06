package dev.androidbroadcast.devto.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemsIndexed
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues
import dev.androidbroadcast.devto.home.model.Article
import dev.androidbroadcast.devto.theme.Dimens
import dev.androidbroadcast.devto.theme.Typography
import dev.androidbroadcast.devto.theme.contentPaddings

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val articlesPagingItems = viewModel.articles.collectAsLazyPagingItems()
    if (articlesPagingItems.loadState.source.refresh == LoadState.Loading) {
        ScreenProgress()
    } else {
        LazyColumn(
            modifier,
            contentPadding = rememberInsetsPaddingValues(
                insets = LocalWindowInsets.current.systemBars,
                applyTop = true,
                applyBottom = true,
            )
        ) {
            itemsIndexed(articlesPagingItems) { _, article ->
                ArticleItem(
                    requireNotNull(article),
                    modifier = Modifier.padding(bottom = Dimens.paddings.small),
                )
            }

            if (articlesPagingItems.loadState.append == LoadState.Loading) {
                item {
                    PageLoadingProgress()
                }
            }
        }
    }
}

@Composable
private fun PageLoadingProgress() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
private fun ScreenProgress() {
    CircularProgressIndicator(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
internal fun ArticleItem(article: Article, modifier: Modifier = Modifier) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Dimens.screenPaddings.start,
                end = Dimens.screenPaddings.end,
                bottom = Dimens.paddings.default,
                top = Dimens.paddings.default,
            )
            .clickable { },
        elevation = 10.dp
    ) {
        Column(modifier) {
            article.imageUrl?.let { imageUrl -> ArticleCover(imageUrl) }
            Column(modifier = Modifier.contentPaddings()) {
                ArticleCreator(creator = article.creator)
                Text(text = article.title, style = MaterialTheme.typography.h6)
                if (article.tags.isNotEmpty()) {
                    Tags(
                        Modifier.padding(
                            top = Dimens.paddings.small,
                            bottom = Dimens.paddings.small
                        ), tags = article.tags
                    )
                }
                Row {
                    Counter(article.positiveReactionsCount, Icons.Outlined.Favorite)
                    Spacer(modifier = Modifier.size(Dimens.paddings.default))
                    Counter(article.commentsCount, Icons.Filled.Face)
                }
            }
        }
    }
}

@Composable
private fun Counter(count: Int, image: ImageVector, modifier: Modifier = Modifier) {
    Row(modifier) {
        Icon(image, contentDescription = null)
        Spacer(modifier = Modifier.size(Dimens.paddings.small))
        Text(text = count.toString())
    }
}

@Composable
private fun ArticleCover(image: Any, modifier: Modifier = Modifier) {
    Image(
        painter = rememberImagePainter(data = image),
        contentDescription = null,
        alignment = Alignment.Center,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 150.dp)
            .then(modifier)
    )
}

@Composable
@Preview(showBackground = true)
private fun Tags(
    modifier: Modifier = Modifier,
    tags: List<String> = listOf("android", "compose", "jetpack", "broadcast")
) {
    if (tags.isNotEmpty()) Spacer(modifier = Modifier.size(Dimens.paddings.small))
    FlowRow(
        modifier,
        crossAxisSpacing = Dimens.paddings.small,
        mainAxisSpacing = Dimens.paddings.small
    ) {
        tags.forEach { tag -> Tag(tag) }
    }
}

@Composable
private fun Tag(
    tag: String,
    modifier: Modifier = Modifier,
    onClick: (tag: String) -> Unit = {},
) {
    Text(
        text = "#${tag}",
        style = Typography.caption,
        modifier = modifier
            .clickable { onClick(tag) }
            .padding(all = Dimens.paddings.small)
    )
}

@Composable
private fun ArticleCreator(creator: Article.User, modifier: Modifier = Modifier) {
    Row(modifier, verticalAlignment = Alignment.CenterVertically) {
        creator.profileImageUrl?.let { ArticleCreatorPhoto(it) }
        Spacer(modifier = Modifier.size(Dimens.paddings.small))
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

@Composable
private fun ArticleCreatorPhoto(image: Any, modifier: Modifier = Modifier) {
    Image(
        painter = rememberImagePainter(
            data = image,
            builder = { transformations(CircleCropTransformation()) }
        ),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .size(32.dp)
            .then(modifier)
    )
}
