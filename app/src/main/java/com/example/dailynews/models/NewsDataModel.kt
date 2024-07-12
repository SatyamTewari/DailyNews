package com.example.dailynews.models

data class NewsDataModel(
    val articles: List<Article> = emptyList(),
    val status: String? = null,
    val totalResults: Int = 0
)