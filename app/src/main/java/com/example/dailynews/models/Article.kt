package com.example.dailynews.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dailynews.utils.Constants.NEWS_ARTICLE_TABLE
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = NEWS_ARTICLE_TABLE)
data class Article(
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val url: String? = null,
    @Embedded
    val source: Source? = null,
    val author: String? = null,
)