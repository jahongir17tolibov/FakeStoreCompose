package com.jt17.fakestorecompose.core.navigation.graph

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.core.navigation.BOTTOM_NAV_ROUTE
import com.jt17.fakestorecompose.core.navigation.BottomNavBarDestinations
import com.jt17.fakestorecompose.presentation.screens.bottom_nav.CartScreen
import com.jt17.fakestorecompose.presentation.screens.bottom_nav.HomeRoute
import com.jt17.fakestorecompose.presentation.screens.bottom_nav.ProfileScreen


@RequiresApi(Build.VERSION_CODES.P)
fun NavGraphBuilder.bottomNavGraph(
    navController: NavHostController,
    onProvideBaseViewModel: (baseViewModel: BaseViewModel) -> Unit,
) {

    navigation(
        startDestination = BottomNavBarDestinations.Home.route,
        route = BOTTOM_NAV_ROUTE
    ) {

        composable(route = BottomNavBarDestinations.Home.route) {
            HomeRoute(onProvideBaseViewModel = onProvideBaseViewModel)
        }

        composable(route = BottomNavBarDestinations.Cart.route) {
            CartScreen(navController = navController)
        }

        composable(route = BottomNavBarDestinations.Profile.route) {
            ProfileScreen(navController = navController)
        }

    }

}