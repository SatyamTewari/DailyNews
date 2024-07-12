package com.example.dailynews.views.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun NewsDetail(title: String){
    Box(modifier = Modifier.fillMaxWidth(1f).fillMaxHeight(1f), contentAlignment = Alignment.Center) {
        Text(text = title)
    }
}