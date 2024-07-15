package com.example.dailynews.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.dailynews.data.local.NewsDatabase
import com.example.dailynews.data.paging.NewsRemoteMediator
import com.example.dailynews.data.paging.SearchPagingSource
import com.example.dailynews.data.remote.NewsAPI
import com.example.dailynews.models.Article
import com.example.dailynews.utils.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class Repository @Inject constructor(
    private val newsAPI: NewsAPI,
    private val database: NewsDatabase
) {

    fun getAllNews(): Flow<PagingData<Article>> {
        val pagingSourceFactory = {
            database.newsDao().getAllNews()
        }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = NewsRemoteMediator(
                newsAPI = newsAPI,
                database = database
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }

    fun searchNews(query: String): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(newsAPI = newsAPI, query = query)
            }
        ).flow
    }
}