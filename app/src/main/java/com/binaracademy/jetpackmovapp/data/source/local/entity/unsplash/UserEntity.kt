package com.binaracademy.jetpackmovapp.data.source.local.entity.unsplash

import androidx.room.Embedded

data class UserEntity(
    @Embedded
    val userLinks: UserLinksEntity,
    val username: String
)
