package com.example.dailynews.views.util

import com.example.dailynews.models.Article

data class ScreenState(
    val isLoading: Boolean = false,
    val items: List<Article> = emptyList(),
    val error: String? = null,
    val endReached: Boolean = false,
    val page: Int = 0
)
