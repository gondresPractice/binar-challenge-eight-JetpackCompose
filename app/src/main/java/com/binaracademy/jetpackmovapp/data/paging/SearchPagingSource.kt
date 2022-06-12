package com.binaracademy.jetpackmovapp.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.binaracademy.jetpackmovapp.data.source.remote.network.ApiService
import com.binaracademy.jetpackmovapp.domain.model.UnsplashImage
import com.binaracademy.jetpackmovapp.mapper.Mapper.mapUnsplashImageResponsesToDomain

class SearchPagingSource(
    private val apiService: ApiService,
    private val query: String
) : PagingSource<Int, UnsplashImage>() {
    override fun getRefreshKey(state: PagingState<Int, UnsplashImage>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashImage> {
        return try {
            val position = params.key ?: INITIAL_PAGE_INDEX
            val responseData = apiService.searchImages(query, position, 10).results

            LoadResult.Page(
                data = responseData.mapUnsplashImageResponsesToDomain(),
                prevKey = if (position == INITIAL_PAGE_INDEX) null else position - 1,
                nextKey = if (responseData.isEmpty()) null else position + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val INITIAL_PAGE_INDEX = 1
    }

}