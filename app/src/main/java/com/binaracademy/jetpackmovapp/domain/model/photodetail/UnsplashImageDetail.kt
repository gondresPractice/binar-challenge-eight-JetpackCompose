package com.binaracademy.jetpackmovapp.domain.model.photodetail

import com.binaracademy.jetpackmovapp.domain.model.Urls
import com.binaracademy.jetpackmovapp.domain.model.User

data class UnsplashImageDetail(
    val id: String,
    val description: String?,
    val urls: Urls,
    val user: User,
    val exif: Exif
)
