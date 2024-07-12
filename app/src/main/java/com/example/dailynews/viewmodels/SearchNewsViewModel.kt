package com.example.dailynews.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailynews.models.Article
import com.example.dailynews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    private var searchKey = ""
    private var searchJob: Job? = null

    val searchedNewsResult: StateFlow<List<Article>>
        get() = repository.searchedNews

    fun searchNewsWithKey(key: String) {
        viewModelScope.launch {
            searchJob?.cancelAndJoin()
            searchJob = launch(Dispatchers.IO) {
                searchKey = key
                if(searchKey.isNotEmpty()) {
                    repository.getSearchedNews(key, 1)
                } else {

                }
            }
        }
    }

//    fun loadMoreNews(){
//        viewModelScope.launch(Dispatchers.IO) {
//            repository.getSearchedNews(searchKey.value, 2, 5)
//        }
//    }
}