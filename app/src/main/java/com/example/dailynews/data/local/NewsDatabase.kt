package com.example.dailynews.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dailynews.data.local.dao.NewsDao
import com.example.dailynews.data.local.dao.NewsRemoteKeysDao
import com.example.dailynews.models.Article
import com.example.dailynews.models.NewsRemoteKeys

@Database(entities = [NewsRemoteKeys::class, Article::class], version = 1)
abstract class NewsDatabase: RoomDatabase() {

    abstract fun newsDao(): NewsDao
    abstract fun newsRemoteKeysDao(): NewsRemoteKeysDao
}