package com.example.dailynews.api

import com.example.dailynews.models.NewsDataModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getDailyNews(
        @Query("apiKey") apiKey: String,
        @Query("category") category: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<NewsDataModel>

    @GET("everything")
    suspend fun getSearchedNews(
        @Query("apiKey") apiKey: String,
        @Query("q") search: String,
        @Query("page") page: Int,
        @Query("pageSize") pageSize: Int
    ): Response<NewsDataModel>
}