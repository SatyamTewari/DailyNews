package com.example.dailynews.views.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailynews.R
import com.example.dailynews.viewmodels.NewsHeadlineViewModel
import com.example.dailynews.views.composables.NewsItem

@Composable
fun NewsHeadline(onSearchNewsClick: () -> Unit, onNewsItemClick: (newsTitle: String) -> Unit) {
    val headlineViewModel: NewsHeadlineViewModel = hiltViewModel()
    val dailyNews = headlineViewModel.dailyNews.collectAsState()
    Column {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(1f)
                .height(20.dp)
                .clickable { onSearchNewsClick.invoke() },
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = "searchIcon"
            )
            Text(text = "Search News")
        }
        LazyColumn {
            items(dailyNews.value) {
                NewsItem(it, onNewsItemClick)
            }
        }
    }
}