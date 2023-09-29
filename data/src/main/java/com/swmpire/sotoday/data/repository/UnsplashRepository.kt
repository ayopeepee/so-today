package com.swmpire.sotoday.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.swmpire.sotoday.data.unsplash.UnsplashApi
import com.swmpire.sotoday.data.unsplash.UnsplashPagingSource
import javax.inject.Inject

class UnsplashRepository @Inject constructor(private val unsplashApi: UnsplashApi) {

    fun getSearchResults(query: String) =
        Pager(
            config = PagingConfig(
                pageSize = 20,
                maxSize = 100,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { UnsplashPagingSource(unsplashApi, query) }
    ).liveData
}