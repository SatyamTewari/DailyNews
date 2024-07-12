package com.example.dailynews.views.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dailynews.viewmodels.SearchNewsViewModel
import com.example.dailynews.views.composables.EmptyHolder
import com.example.dailynews.views.composables.NewsItem

@Composable
fun SearchNews(onclick: (newsTitle: String) -> Unit) {
    val searchNewsViewModel: SearchNewsViewModel = hiltViewModel()
    val searchedNewsResult = searchNewsViewModel.searchedNewsResult.collectAsState()
    val searchKey = rememberSaveable { mutableStateOf("")}
    Column {
        TextField(value = searchKey.value, modifier = Modifier.fillMaxWidth(1f), onValueChange = {
            searchKey.value = it
            searchNewsViewModel.searchNewsWithKey(it)
        })
        if(searchedNewsResult.value.isNotEmpty()) {
            LazyColumn {
                items(searchedNewsResult.value) {
                    NewsItem(it, onclick)
                }
            }
        } else {
            EmptyHolder()
        }
    }
}