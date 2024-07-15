package com.example.dailynews.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.ExperimentalPagingApi
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.dailynews.data.repository.Repository
import com.example.dailynews.models.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: Repository
): ViewModel() {

    private val _searchQuery = mutableStateOf("")
    val searchQuery : State<String>
        get() = _searchQuery

    private val _searchedNews = MutableStateFlow<PagingData<Article>>(PagingData.empty())
    val searchedNews : StateFlow<PagingData<Article>>
        get() = _searchedNews

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun searchHeroes(query: String) {
        viewModelScope.launch {
            repository.searchNews(query = query).cachedIn(viewModelScope).collect {
                _searchedNews.emit(it)
            }
        }
    }
}