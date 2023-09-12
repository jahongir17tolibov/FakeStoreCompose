package com.jt17.fakestorecompose.core.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.core.navigation.AUTH_ROUTE
import com.jt17.fakestorecompose.core.navigation.Destinations
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

    composable(route = Destinations.Login.route) {
        LoginScreen(navController = navController)
    }

    composable(route = Destinations.SignUp.route) {
        SignUpScreen(navController = navController)
    }

}