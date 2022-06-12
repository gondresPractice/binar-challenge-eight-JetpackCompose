package com.binaracademy.jetpackmovapp

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.Lifecycle
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.ExperimentalPagingApi
import coil.annotation.ExperimentalCoilApi
import com.binaracademy.jetpackmovapp.ui.photodetail.PhotoDetailScreen
import com.binaracademy.jetpackmovapp.ui.UnsplashTabs
import com.binaracademy.jetpackmovapp.ui.login.LoginScreen
import com.binaracademy.jetpackmovapp.ui.search.SearchScreen
import com.binaracademy.jetpackmovapp.ui.signup.SignUpScreen
import com.binaracademy.jetpackmovapp.ui.unsplash

@OptIn(ExperimentalPagingApi::class, ExperimentalCoilApi::class)
@Composable
fun NavGraph(
    modifier: Modifier = Modifier,
    finishActivity: () -> Unit = {},
    navController: NavHostController = rememberNavController(),
    startDestination: String = Screen.Home.route,
    isUserHasLoggedIn: Boolean
) {
    val isUserHasLoggedInState = remember(isUserHasLoggedIn) {
        mutableStateOf(isUserHasLoggedIn)
    }

    val actions = remember(navController) { MainActions(navController) }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.Login.route) {
            BackHandler {
                finishActivity()
            }

            LoginScreen(navController)
        }

        composable(Screen.SignUp.route) {
            SignUpScreen(navController)
        }

        composable(Screen.Search.route) {
            SearchScreen(
                navController = navController,
                {
                        id -> actions.openPhoto
                }
            )
        }

        navigation(
            route = Screen.Home.route,
            startDestination = UnsplashTabs.HOME.route
        ) {
            unsplash(
                onPhotoSelected = actions.openPhoto,
                isUserHasLoggedIn = isUserHasLoggedInState,
                navController = navController,
                modifier = modifier
            )
        }

        composable(
            "${Screen.PhotoDetail.route}/{$PHOTO_DETAIL_KEY_ID_KEY}",
            arguments = listOf(
                navArgument(PHOTO_DETAIL_KEY_ID_KEY) { type = NavType.StringType }
            )
        ) { backStackEntry: NavBackStackEntry ->
            val arguments = requireNotNull(backStackEntry.arguments)
            val currentPhotoId = arguments.getString(PHOTO_DETAIL_KEY_ID_KEY)
            if (currentPhotoId != null) {
                PhotoDetailScreen(
                    photoId = currentPhotoId,
                    upPress = { actions.upPress(backStackEntry) }
                )
            }
        }
    }
}

class MainActions(navController: NavHostController) {
    val openPhoto = { newPhotoId: String, from: NavBackStackEntry ->
        if (from.lifecycleIsResumed()) {
            navController.navigate("${Screen.PhotoDetail.route}/$newPhotoId")
        }
    }

    val upPress: (from: NavBackStackEntry) -> Unit = { from ->
        if (from.lifecycleIsResumed()) {
            navController.navigateUp()
        }
    }
}

private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED