package com.example.dailynews.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dailynews.models.Article

@Dao
interface NewsDao {

    // since room supports paging library we don't have to do anything extra and simply specify PagingSource as return type and rest can be handled on its own
    @Query("SELECT * FROM news_article_table")
    fun getAllNews(): PagingSource<Int, Article> // => Int - for page No; UnsplashImage for data type of each item

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNews(articles: List<Article>)

    @Query("DELETE FROM news_article_table")
    suspend fun deleteAllNews()
}