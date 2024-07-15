package com.example.dailynews.views.common

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.dailynews.R
import com.example.dailynews.models.Article
import com.example.dailynews.models.Source

@ExperimentalCoilApi
@Composable
fun ListContent(lazyPagingItems: LazyPagingItems<Article>, onNewsItemClicked: (title: String)->Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            count = lazyPagingItems.itemCount,
            key = lazyPagingItems.itemKey { it.title },
            contentType = lazyPagingItems.itemContentType { null }
        ) { index ->
            lazyPagingItems[index]?.let { NewsItem(newsArticle = it, onNewsItemClicked) }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun NewsItem(newsArticle: Article, onNewsItemClicked: (title: String)->Unit) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clickable {
                onNewsItemClicked(newsArticle.title)
            },
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            AsyncImage(
                model = ImageRequest.Builder(context)
                    .data(newsArticle.url)
                    .crossfade(true)
                    .build(),
                placeholder = painterResource(id = R.drawable.ic_image_placeholder),
                error = painterResource(id = R.drawable.ic_image_placeholder),
                contentDescription = "News Image",
                modifier = Modifier.height(70.dp).width(70.dp),
                contentScale = ContentScale.Crop
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(start = 15.dp),
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("title: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                            append(newsArticle.title)
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = buildAnnotatedString {
                        append("source: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                            append(newsArticle.source?.name)
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Text(
                    text = buildAnnotatedString {
                        append("author: ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                            append(newsArticle.author)
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
@Preview
fun NewsListPreview() {
    NewsItem(
        newsArticle = Article(
            title = "Testing for Headline News",
            url = "",
            source = Source(
                name = "Google News"
            ),
            author = "Satyam Tewari",
        ),
        {}
    )
}