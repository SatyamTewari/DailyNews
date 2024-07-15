package com.example.dailynews.views.screens.detail

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalMaterial3Api
@Composable
fun DetailScreen(title: String?) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Detail",
                        color = Color.Black
                    )
                },
                colors = TopAppBarColors(Color.White, Color.White, Color.White, Color.White, Color.White)
            )
        },
        content = {
            Box(modifier = Modifier.padding(it).fillMaxHeight().fillMaxWidth(), contentAlignment = Alignment.Center) {
                Text(
                    text = title ?: "Default Title",
                    color = Color.Black,
                    modifier = Modifier.padding(20.dp)
                )
            }
        }
    )
}