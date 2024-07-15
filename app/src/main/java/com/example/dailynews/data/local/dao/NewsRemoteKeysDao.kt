package com.example.dailynews.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dailynews.models.NewsRemoteKeys

@Dao
interface NewsRemoteKeysDao {

    @Query("SELECT * FROM news_remote_keys_table WHERE title =:title")
    suspend fun getRemoteKeys(title: String): NewsRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<NewsRemoteKeys>)

    @Query("DELETE FROM news_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}