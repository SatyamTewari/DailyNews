package com.example.dailynews.repository

import com.example.dailynews.api.NewsAPI
import com.example.dailynews.models.NewsDataModel
import com.example.dailynews.utils.Constants.API_KEY
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsAPI: NewsAPI
) {

    private val _dailyNews = MutableStateFlow(NewsDataModel())
    val dailyNews: StateFlow<NewsDataModel>
        get() = _dailyNews

    private val _searchedNews = MutableStateFlow(NewsDataModel())
    val searchedNews: StateFlow<NewsDataModel>
        get() = _searchedNews

    suspend fun getDailyNews(category: String, page: Int, pageSize: Int){
        val response = newsAPI.getDailyNews(API_KEY, category, page, pageSize)
        if (response.isSuccessful && response.body() != null) {
            _dailyNews.emit(response.body()!!)
        }
    }

    suspend fun getSearchedNews(search: String, page: Int, pageSize: Int){
        val response = newsAPI.getSearchedNews(API_KEY, search, page, pageSize)
        if (response.isSuccessful && response.body() != null) {
            _searchedNews.emit(response.body()!!)
        }
    }

}