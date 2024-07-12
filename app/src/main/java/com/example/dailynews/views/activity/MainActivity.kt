package com.example.dailynews.views.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.dailynews.ui.theme.DailyNewsTheme
import com.example.dailynews.views.screens.NewsDetail
import com.example.dailynews.views.screens.NewsHeadline
import com.example.dailynews.views.screens.SearchNews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyNewsTheme {
                Scaffold{
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "newsHeadlineScreen") {
        composable(route = "newsHeadlineScreen") {
            NewsHeadline(
                onSearchNewsClick = {navController.navigate("searchNewsScreen")},
                onNewsItemClick = {navController.navigate("newsDetailScreen/${it}")}
            )
        }
        composable(route = "searchNewsScreen") {
            SearchNews{
                navController.navigate("newsDetailScreen/${it}")
            }
        }
        composable(route = "newsDetailScreen/{newsTitle}",
            arguments = listOf(
                navArgument("newsTitle") {
                    type = NavType.StringType
                }
            )
        ) { navBackStackEntry ->
            NewsDetail(title = navBackStackEntry.arguments?.getString("newsTitle") ?: "")
        }
    }
}