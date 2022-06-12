package com.binaracademy.jetpackmovapp.data.source.remote.network

import com.binaracademy.jetpackmovapp.BuildConfig
import com.binaracademy.jetpackmovapp.data.source.remote.response.SearchResultResponse
import com.binaracademy.jetpackmovapp.data.source.remote.response.UnsplashImageResponse
import com.binaracademy.jetpackmovapp.data.source.remote.response.photodetail.UnsplashImageDetailResponse
import retrofit2.http.*

interface ApiService {
    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos")
    suspend fun getAllImages(
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): List<UnsplashImageResponse>

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/search/photos")
    suspend fun searchImages(
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): SearchResultResponse

    @Headers("Authorization: Client-ID ${BuildConfig.API_KEY}")
    @GET("/photos/{id}")
    suspend fun getPhotoDetail(
        @Path("id") id: String
    ): UnsplashImageDetailResponse
}
