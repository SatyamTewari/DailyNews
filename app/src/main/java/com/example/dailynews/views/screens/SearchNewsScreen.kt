package com.example.dailynews.views.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailynews.viewmodels.SearchNewsViewModel
import com.example.dailynews.views.components.NewsItem

@Composable
fun SearchNews(){
    val searchNewsViewModel: SearchNewsViewModel = hiltViewModel()
    val searchedNews = searchNewsViewModel.searchedNews.collectAsState()
    Column {
        TextField(value = "", modifier = Modifier.fillMaxWidth(1f), onValueChange = {})
        LazyColumn {
            items(searchedNews.value) {
                NewsItem(it)
            }
        }
    }
}