package com.example.dailynews.views.util

interface Paginator<Key, Item> {

    suspend fun loadNextItems()
    fun reset()
}