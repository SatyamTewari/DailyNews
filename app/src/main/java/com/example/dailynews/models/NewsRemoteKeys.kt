package com.example.dailynews.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dailynews.utils.Constants.NEWS_REMOTE_KEYS_TABLE

@Entity(tableName = NEWS_REMOTE_KEYS_TABLE)
data class NewsRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val title: String,
    val prevPage: Int?,
    val nextPage: Int?
)
