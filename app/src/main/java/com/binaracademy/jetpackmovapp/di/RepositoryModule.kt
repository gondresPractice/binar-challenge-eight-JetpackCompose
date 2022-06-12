package com.binaracademy.jetpackmovapp.di

import com.binaracademy.jetpackmovapp.data.UnsplashRepository
import com.binaracademy.jetpackmovapp.data.UnsplashRepositoryImpl
import com.binaracademy.jetpackmovapp.data.UserRepository
import com.binaracademy.jetpackmovapp.data.UserRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideUnsplashRepository(unsplashRepositoryImpl: UnsplashRepositoryImpl): UnsplashRepository

    @Binds
    abstract fun provideUserRepository(userRepositoryImpl: UserRepositoryImpl): UserRepository
}