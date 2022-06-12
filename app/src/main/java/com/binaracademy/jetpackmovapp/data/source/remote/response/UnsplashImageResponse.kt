package com.binaracademy.jetpackmovapp.data.source.remote.response

import androidx.room.Embedded
import androidx.room.PrimaryKey
import com.binaracademy.jetpackmovapp.data.source.local.entity.unsplash.UrlsEntity
import com.binaracademy.jetpackmovapp.data.source.local.entity.unsplash.UserEntity

data class UnsplashImageResponse(
    val id: String,
    val urls: UrlsResponse,
    val likes: Int,
    val user: UserResponse
)
