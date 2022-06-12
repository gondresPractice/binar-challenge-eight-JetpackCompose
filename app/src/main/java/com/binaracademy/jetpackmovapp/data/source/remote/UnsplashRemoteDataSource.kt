package com.binaracademy.jetpackmovapp.data.source.remote

import com.binaracademy.jetpackmovapp.data.source.remote.network.ApiService
import com.binaracademy.jetpackmovapp.data.source.remote.response.photodetail.UnsplashImageDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UnsplashRemoteDataSource @Inject constructor(
    private val apiService: ApiService
){
    fun getPhotoDetail(id: String): Flow<UnsplashImageDetailResponse> = flow {
        val photoDetail = apiService.getPhotoDetail(id)
        emit(photoDetail)
    }
}