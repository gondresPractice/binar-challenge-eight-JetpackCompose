package com.binaracademy.jetpackmovapp.data

import androidx.paging.*
import com.binaracademy.jetpackmovapp.data.paging.SearchPagingSource
import com.binaracademy.jetpackmovapp.data.paging.UnsplashRemoteMediator
import com.binaracademy.jetpackmovapp.data.source.local.entity.unsplash.UnsplashImageEntity
import com.binaracademy.jetpackmovapp.data.source.local.room.AppDatabase
import com.binaracademy.jetpackmovapp.data.source.remote.UnsplashRemoteDataSource
import com.binaracademy.jetpackmovapp.data.source.remote.network.ApiService
import com.binaracademy.jetpackmovapp.domain.model.UnsplashImage
import com.binaracademy.jetpackmovapp.domain.model.photodetail.UnsplashImageDetail
import com.binaracademy.jetpackmovapp.mapper.Mapper.mapUnsplashImageDetailResponseToDomain
import com.binaracademy.jetpackmovapp.mapper.Mapper.mapUnsplashImageEntityToDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject
import javax.inject.Singleton

interface UnsplashRepository {
    fun getAllImages(): Flow<PagingData<UnsplashImage>>
    fun searchImages(query: String): Flow<PagingData<UnsplashImage>>
    suspend fun getPhotoDetail(id: String): Flow<UnsplashImageDetail>
}

@Singleton
class UnsplashRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val appDatabase: AppDatabase,
    private val dataSource: UnsplashRemoteDataSource
) : UnsplashRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getAllImages(): Flow<PagingData<UnsplashImage>> {
        val pagingSourceFactory = {
            appDatabase.unsplashImageDao().getAllImages()
        }
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = UnsplashRemoteMediator(
                apiService = apiService,
                appDatabase = appDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map {
                it.mapUnsplashImageEntityToDomain()
            }
        }
    }

    override fun searchImages(query: String): Flow<PagingData<UnsplashImage>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                SearchPagingSource(apiService, query)
            }
        ).flow
    }

    override suspend fun getPhotoDetail(id: String): Flow<UnsplashImageDetail> {
        return dataSource.getPhotoDetail(id).map {
            it.mapUnsplashImageDetailResponseToDomain()
        }
    }
}