package com.binaracademy.jetpackmovapp.domain.usecase

import com.binaracademy.jetpackmovapp.data.UnsplashRepository
import com.binaracademy.jetpackmovapp.domain.model.photodetail.UnsplashImageDetail
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPhotoDetailUseCase @Inject constructor(
    private val repository: UnsplashRepository
){
    suspend operator fun invoke(id: String): Flow<UnsplashImageDetail> {
        return repository.getPhotoDetail(id)
    }
}