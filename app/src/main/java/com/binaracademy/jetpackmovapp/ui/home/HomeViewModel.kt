package com.binaracademy.jetpackmovapp.ui.home

import androidx.lifecycle.ViewModel
import com.binaracademy.jetpackmovapp.data.UnsplashRepository
import com.binaracademy.jetpackmovapp.domain.usecase.GetImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    getImagesUseCase: GetImagesUseCase
) : ViewModel() {

    val getImages = getImagesUseCase.invoke()
}