package com.example.dailynews.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dailynews.models.Article
import com.example.dailynews.models.NewsDataModel
import com.example.dailynews.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsHeadlineViewModel @Inject constructor(
    private val repository: NewsRepository,
//    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    val dailyNews: StateFlow<List<Article>>
        get() = repository.dailyNews

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getDailyNews("sports", 1)
        }
    }
}