package com.binaracademy.jetpackmovapp.domain.usecase

import androidx.paging.PagingData
import com.binaracademy.jetpackmovapp.data.UnsplashRepository
import com.binaracademy.jetpackmovapp.domain.model.UnsplashImage
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchImagesUseCase @Inject constructor(
    private val repository: UnsplashRepository
) {
    operator fun invoke(query: String): Flow<PagingData<UnsplashImage>> {
        return repository.searchImages(query)
    }
}