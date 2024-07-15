package com.example.dailynews.data.remote

import com.example.dailynews.models.NewsDataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getDailyNews(
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int,
    ): NewsDataModel

    @GET("everything")
    suspend fun getSearchedNews(
        @Query("q") query: String,
        @Query("pageSize") pageSize: Int
    ): NewsDataModel
}