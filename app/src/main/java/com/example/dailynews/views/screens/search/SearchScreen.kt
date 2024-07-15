package com.example.dailynews.views.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.annotation.ExperimentalCoilApi
import com.example.dailynews.navigation.Screen
import com.example.dailynews.viewmodels.SearchViewModel
import com.example.dailynews.views.common.ListContent
import com.example.dailynews.views.common.NewsItem


@ExperimentalPagingApi
@ExperimentalCoilApi
@ExperimentalMaterial3Api
@Composable
fun SearchScreen(
    navController: NavHostController,
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    val searchQuery = searchViewModel.searchQuery
    val searchedNews = searchViewModel.searchedNews.collectAsLazyPagingItems()

    Scaffold(
        content = {
            Box(modifier = Modifier.padding(it)) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(all = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    item {
                        SearchWidget(
                            text = searchQuery.value,
                            onTextChange = {
                                searchViewModel.updateSearchQuery(query = it)
                            },
                            onSearchClicked = {
                                searchViewModel.searchHeroes(query = it)
                            },
                            onCloseClicked = {
                                navController.popBackStack()
                            }
                        )
                    }
                    items(
                        count = searchedNews.itemCount,
                        key = searchedNews.itemKey { it.title },
                        contentType = searchedNews.itemContentType { null }
                    ) { index ->
                        searchedNews[index]?.let {
                            NewsItem(newsArticle = it, onNewsItemClicked = { title ->
                                navController.navigate("${Screen.Detail.route}/$title")
                            })
                        }
                    }
                }
            }
        }
    )
}