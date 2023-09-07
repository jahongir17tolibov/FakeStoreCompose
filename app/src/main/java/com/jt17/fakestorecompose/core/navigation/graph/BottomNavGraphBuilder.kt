package com.jt17.fakestorecompose.core.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.jt17.fakestorecompose.core.navigation.BOTTOM_NAV_ROUTE
import com.jt17.fakestorecompose.core.navigation.BottomNavBarDestinations
import com.jt17.fakestorecompose.presentation.screens.bottomnav.CartScreen
import com.jt17.fakestorecompose.presentation.screens.bottomnav.HomeScreen
import com.jt17.fakestorecompose.presentation.screens.bottomnav.ProfileScreen


fun NavGraphBuilder.bottomNavGraph(navController: NavHostController) {

    navigation(
        startDestination = BottomNavBarDestinations.Home.route,
        route = BOTTOM_NAV_ROUTE
    ) {

        composable(route = BottomNavBarDestinations.Home.route) {
            HomeScreen(navController = navController)
        }

        composable(route = BottomNavBarDestinations.Cart.route) {
            CartScreen(navController = navController)
        }

        composable(route = BottomNavBarDestinations.Profile.route) {
            ProfileScreen(navController = navController)
        }

    }

}