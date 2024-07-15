package com.example.dailynews.views.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.dailynews.navigation.Screen
import com.example.dailynews.viewmodels.HomeViewModel
import com.example.dailynews.views.common.ListContent

@ExperimentalCoilApi
@ExperimentalPagingApi
@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val getAllNews = homeViewModel.getAllNews.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = {
                    navController.navigate(Screen.Search.route)
                }
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                ListContent(
                    lazyPagingItems = getAllNews,
                    onNewsItemClicked = { title ->
                        navController.navigate("${Screen.Detail.route}/$title")
                    })
            }
        }
    )
}