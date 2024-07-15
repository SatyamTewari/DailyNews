package com.example.dailynews.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.dailynews.data.remote.NewsAPI
import com.example.dailynews.models.Article
import com.example.dailynews.utils.Constants.ITEMS_PER_PAGE

class SearchPagingSource(
    private val newsAPI: NewsAPI,
    private val query: String
) : PagingSource<Int, Article>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val currentPage = params.key ?: 1
        return try {
            val response = newsAPI.getSearchedNews(query = query, pageSize = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.articles.isEmpty()
            if (response.articles.isNotEmpty()) {
                LoadResult.Page(
                    data = response.articles,
                    prevKey = if (currentPage == 1) null else currentPage - 1,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition
    }

}