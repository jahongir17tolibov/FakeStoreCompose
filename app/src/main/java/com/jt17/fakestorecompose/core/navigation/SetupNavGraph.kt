package com.jt17.fakestorecompose.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jt17.fakestorecompose.core.navigation.graph.bottomNavGraph

@Composable
fun SetupNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = BOTTOM_NAV_ROUTE,
        route = ROOT_ROUTE
    ) {

        bottomNavGraph(navController = navController)

    }

}