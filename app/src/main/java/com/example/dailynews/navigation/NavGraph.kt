package com.example.dailynews.navigation

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.example.dailynews.views.screens.detail.DetailScreen
import com.example.dailynews.views.screens.home.HomeScreen
import com.example.dailynews.views.screens.search.SearchScreen

@ExperimentalPagingApi
@ExperimentalCoilApi
@ExperimentalMaterial3Api
@Composable
fun SetupNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(route = Screen.Home.route){
            HomeScreen(navController = navController)
        }
        composable(route = Screen.Search.route){
            SearchScreen(navController = navController)
        }
        composable(route = "${Screen.Detail.route}/{title}",
            arguments = listOf(navArgument("title"){
                type = NavType.StringType
            })
        ){ backStackEntry ->
            val title = backStackEntry.arguments?.getString("title")
            DetailScreen(title)
        }
    }
}