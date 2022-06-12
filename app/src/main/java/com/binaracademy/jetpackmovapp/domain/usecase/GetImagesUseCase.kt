package com.binaracademy.jetpackmovapp.domain.usecase

import androidx.paging.PagingData
import com.binaracademy.jetpackmovapp.data.UnsplashRepository
import com.binaracademy.jetpackmovapp.data.source.local.entity.unsplash.UnsplashImageEntity
import com.binaracademy.jetpackmovapp.domain.model.UnsplashImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetImagesUseCase @Inject constructor(
    private val unsplashRepository: UnsplashRepository
) {
    operator fun invoke(): Flow<PagingData<UnsplashImage>> {
        return unsplashRepository.getAllImages()
    }
}