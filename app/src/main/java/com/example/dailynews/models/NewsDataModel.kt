package com.example.dailynews.models

import kotlinx.serialization.Serializable

@Serializable
data class NewsDataModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)