package com.binaracademy.jetpackmovapp.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.binaracademy.jetpackmovapp.data.source.local.entity.UserEntity
import com.binaracademy.jetpackmovapp.data.source.local.entity.unsplash.UnsplashImageEntity
import com.binaracademy.jetpackmovapp.data.source.local.entity.unsplash.UnsplashRemoteKeys

@Database(
    entities = [UserEntity::class, UnsplashImageEntity::class, UnsplashRemoteKeys::class],
    version = 2,
    exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun unsplashImageDao(): UnsplashImageDao
    abstract fun unsplashRemoteKeysDao(): UnsplashRemoteKeysDao
}