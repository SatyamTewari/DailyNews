package com.example.dailynews.repository

import com.example.dailynews.api.NewsAPI
import com.example.dailynews.models.Article
import com.example.dailynews.utils.Constants.API_KEY
import com.example.dailynews.utils.Constants.COUNTRY_CODE
import com.example.dailynews.utils.Constants.PAGE_SIZE
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class NewsRepository @Inject constructor(
    private val newsAPI: NewsAPI
) {

    private val _dailyNews = MutableStateFlow<List<Article>>(emptyList())
    val dailyNews: StateFlow<List<Article>>
        get() = _dailyNews

    private val _searchedNews = MutableStateFlow<List<Article>>(emptyList())
    val searchedNews: StateFlow<List<Article>>
        get() = _searchedNews

    suspend fun getDailyNews(category: String, page: Int){
        val response = newsAPI.getDailyNews(API_KEY, category, COUNTRY_CODE, page, PAGE_SIZE)
        if (response.isSuccessful && response.body() != null && response.body()!!.articles.isNotEmpty()) {
            _dailyNews.emit(response.body()!!.articles)
        }
    }

    suspend fun getSearchedNews(search: String, page: Int){
        val response = newsAPI.getSearchedNews(API_KEY, search, page, PAGE_SIZE)
        if (response.isSuccessful && response.body() != null && response.body()!!.articles.isNotEmpty()) {
            _searchedNews.emit(response.body()!!.articles)
        }
    }

}