package com.jt17.fakestorecompose.core.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.core.navigation.AUTH_ROUTE
import com.jt17.fakestorecompose.core.navigation.BOTTOM_NAV_ROUTE
import com.jt17.fakestorecompose.core.navigation.Destinations
import com.jt17.fakestorecompose.core.navigation.SIGNUP_SCREEN_ROUTE
import com.jt17.fakestorecompose.presentation.screens.auth.LoginRoute
import com.jt17.fakestorecompose.presentation.screens.auth.LoginScreen
import com.jt17.fakestorecompose.presentation.screens.auth.SignUpScreen

@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController,
    onProvideBaseViewModel: (baseViewModel: BaseViewModel) -> Unit,
) = navigation(
    startDestination = Destinations.Login.route,
    route = AUTH_ROUTE
) {

    composable(route = Destinations.Login.route,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(durationMillis = 400, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Up
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(durationMillis = 400, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.Down
            )
        }) {
        LoginRoute(
            onNavigateSignUpScreen = {
                navController.navigate(route = SIGNUP_SCREEN_ROUTE)
            },
            onNavigateMainScreen = {
                navController.navigate(route = BOTTOM_NAV_ROUTE)
            },
            onProvideBaseViewModel = onProvideBaseViewModel
        )
    }

    composable(route = Destinations.SignUp.route,
        enterTransition = {
            slideIntoContainer(
                animationSpec = tween(durationMillis = 400, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Up
            )
        },
        exitTransition = {
            slideOutOfContainer(
                animationSpec = tween(durationMillis = 400, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.Down
            )
        }) {
        SignUpScreen(navController = navController)
    }

}