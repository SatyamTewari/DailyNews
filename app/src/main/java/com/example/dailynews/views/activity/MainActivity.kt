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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dailynews.ui.theme.DailyNewsTheme
import com.example.dailynews.views.screens.NewsDetail
import com.example.dailynews.views.screens.NewsHeadline
import com.example.dailynews.views.screens.SearchNews
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DailyNewsTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {  },
                            colors = TopAppBarColors(
                                containerColor = Color.Blue,
                                scrolledContainerColor = Color.Blue,
                                navigationIconContentColor = Color.Blue,
                                titleContentColor = Color.Blue,
                                actionIconContentColor = Color.Blue
                            ),
                            scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                        )
                    }
                ) {
                    Box(modifier = Modifier.padding(it)) {
                        App()
                    }
                }
            }
        }
    }
}

@Composable
fun App(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "newsHeadlineScreen"){
        composable(route = "newsHeadlineScreen"){
            NewsHeadline()
        }
        composable(route = "searchNewsScreen"){
            SearchNews()
        }
        composable(route = "newsDetailScreen"){
            NewsDetail(title = "random")
        }
    }
}