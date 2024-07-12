package com.example.dailynews.views.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailynews.viewmodels.NewsHeadlineViewModel
import com.example.dailynews.views.components.NewsItem

@Composable
fun NewsHeadline(){
    val headlineViewModel: NewsHeadlineViewModel = hiltViewModel()
    val dailyNews = headlineViewModel.dailyNews.collectAsState()
    LazyColumn {
        items(dailyNews.value){
            NewsItem(it)
        }
    }
}