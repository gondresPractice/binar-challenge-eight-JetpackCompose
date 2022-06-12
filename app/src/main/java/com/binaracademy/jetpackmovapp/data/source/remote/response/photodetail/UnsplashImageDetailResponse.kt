package com.binaracademy.jetpackmovapp.data.source.remote.response.photodetail

import com.binaracademy.jetpackmovapp.data.source.remote.response.UrlsResponse
import com.binaracademy.jetpackmovapp.data.source.remote.response.UserResponse

data class UnsplashImageDetailResponse(
    val id: String,
    val description: String,
    val urls: UrlsResponse,
    val user: UserResponse,
    val exif: ExifResponse
)
