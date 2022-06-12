package com.binaracademy.jetpackmovapp.domain.model

data class UnsplashImage(
    val id: String,
    val urls: Urls,
    val likes: Int,
    val user: User
)
