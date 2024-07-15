package com.example.dailynews.data.paging

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.dailynews.data.local.NewsDatabase
import com.example.dailynews.data.remote.NewsAPI
import com.example.dailynews.models.Article
import com.example.dailynews.models.NewsRemoteKeys
import com.example.dailynews.utils.Constants.ITEMS_PER_PAGE

@ExperimentalPagingApi
class NewsRemoteMediator (
    private val newsAPI: NewsAPI,
    private val database: NewsDatabase
) : RemoteMediator<Int, Article>() {

    private val newsDao = database.newsDao()
    private val newsRemoteKeysDao = database.newsRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, Article>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }

                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }

                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = newsAPI.getDailyNews(category = "sports", page = currentPage, pageSize = ITEMS_PER_PAGE)
            val endOfPaginationReached = response.articles.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            database.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    newsDao.deleteAllNews()
                    newsRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.articles.map { article ->
                    NewsRemoteKeys(
                        title = article.title,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                newsRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                newsDao.addNews(articles = response.articles)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, Article>
    ): NewsRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.title?.let { title ->
                newsRemoteKeysDao.getRemoteKeys(title = title)
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, Article>
    ): NewsRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { article ->
                newsRemoteKeysDao.getRemoteKeys(title = article.title)
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, Article>
    ): NewsRemoteKeys? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { article ->
                newsRemoteKeysDao.getRemoteKeys(title = article.title)
            }
    }
}