package com.binaracademy.jetpackmovapp.di

import com.binaracademy.jetpackmovapp.data.UnsplashRepository
import com.binaracademy.jetpackmovapp.domain.usecase.GetImagesUseCase
import com.binaracademy.jetpackmovapp.domain.usecase.GetPhotoDetailUseCase
import com.binaracademy.jetpackmovapp.domain.usecase.SearchImagesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {

    @Provides
    @ViewModelScoped
    fun provideGetImagesUseCase(repository: UnsplashRepository): GetImagesUseCase {
        return GetImagesUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideSearchImagesUseCase(repository: UnsplashRepository): SearchImagesUseCase {
        return SearchImagesUseCase(repository)
    }

    @Provides
    @ViewModelScoped
    fun provideGetPhotoDetailUseCase(repository: UnsplashRepository): GetPhotoDetailUseCase {
        return GetPhotoDetailUseCase(repository)
    }
}