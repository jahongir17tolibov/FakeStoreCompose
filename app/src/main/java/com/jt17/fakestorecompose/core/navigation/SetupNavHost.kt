package com.jt17.fakestorecompose.core.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.core.navigation.graph.bottomNavGraph

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun SetupNavHost(
    navController: NavHostController,
    modifier: Modifier,
    onProvideBaseViewModel: (baseViewModel: BaseViewModel) -> Unit,
) {

    NavHost(
        navController = navController,
        startDestination = BOTTOM_NAV_ROUTE,
        route = ROOT_ROUTE,
        enterTransition = { EnterTransition.None },
        exitTransition = { ExitTransition.None },
    ) {

        //bottom navigation bars screens graph
        bottomNavGraph(
            navController = navController,
            onProvideBaseViewModel = onProvideBaseViewModel
        )

    }

}