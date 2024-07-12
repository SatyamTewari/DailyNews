package com.example.dailynews.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailynews.models.Article
import com.example.dailynews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchNewsViewModel @Inject constructor(
    private val repository: NewsRepository
) : ViewModel() {

    val searchedNews: StateFlow<List<Article>>
        get() = repository.searchedNews

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getSearchedNews("India", 1, 5)
        }
    }
}