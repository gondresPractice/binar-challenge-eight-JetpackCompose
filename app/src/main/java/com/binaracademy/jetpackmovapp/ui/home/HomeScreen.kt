package com.binaracademy.jetpackmovapp.ui.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.binaracademy.jetpackmovapp.Screen
import com.binaracademy.jetpackmovapp.ui.common.ListContent

@ExperimentalMaterial3Api
@Composable
fun HomeScreen(
    navHostController: NavHostController,
    selectPhoto: (String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val getImages = homeViewModel.getImages.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            HomeTopBar(
                onSearchClicked = { navHostController.navigate(Screen.Search.route) }
            )
        },
        content = {
            ListContent(
                items = getImages,
                modifier = Modifier.padding(it),
                selectPhoto = selectPhoto
            )
        }
    )
}