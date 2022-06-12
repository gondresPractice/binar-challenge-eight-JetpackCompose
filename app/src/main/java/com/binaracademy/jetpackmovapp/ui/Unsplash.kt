package com.binaracademy.jetpackmovapp.ui

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.binaracademy.jetpackmovapp.R
import com.binaracademy.jetpackmovapp.Screen
import com.binaracademy.jetpackmovapp.ui.favorite.FavoriteScreen
import com.binaracademy.jetpackmovapp.ui.home.HomeScreen
import com.binaracademy.jetpackmovapp.ui.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.unsplash(
    onPhotoSelected: (String, NavBackStackEntry) -> Unit,
    isUserHasLoggedIn: State<Boolean>,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    composable(UnsplashTabs.HOME.route) { from ->
        LaunchedEffect(isUserHasLoggedIn) {
            if (!isUserHasLoggedIn.value) {
                navController.navigate(Screen.Login.route)
            }
        }

        if (isUserHasLoggedIn.value) {
            HomeScreen(
                navController,
                {
                   id -> onPhotoSelected(id, from)
                }
            )
        }
    }

    composable(UnsplashTabs.FAVORITE.route) {
        FavoriteScreen()
    }
}
enum class UnsplashTabs(
    @StringRes val title: Int,
    val icon: ImageVector,
    val route: String
) {
    HOME(R.string.home, Icons.Filled.Home, UnsplashDestinations.HOME_ROUTE),
    FAVORITE(R.string.favorite, Icons.Filled.Favorite, UnsplashDestinations.FAVORITE_ROUTE)
}

private object UnsplashDestinations {
    const val HOME_ROUTE = "unsplash/featured"
    const val FAVORITE_ROUTE = "unsplash/my"
}