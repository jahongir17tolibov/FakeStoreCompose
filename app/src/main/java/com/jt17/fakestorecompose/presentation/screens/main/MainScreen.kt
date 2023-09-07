package com.jt17.fakestorecompose.presentation.screens.main

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jt17.fakestorecompose.R
import com.jt17.fakestorecompose.core.navigation.BottomNavBarDestinations
import com.jt17.fakestorecompose.core.navigation.SetupNavGraph
import com.jt17.fakestorecompose.core.ui.components.primaryFont
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme

@Composable
fun MainScreen() {

    val navController = rememberNavController()
    val buttonVisible = remember { mutableStateOf(true) }

    Scaffold(
        bottomBar = {
            BottomBar(navController = navController, state = buttonVisible)
        }
    ) { paddingValues ->

        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            color = MaterialTheme.colorScheme.background
        ) {

            SetupNavGraph(navController = navController)

        }

    }


}

@Composable
fun BottomBar(
    navController: NavHostController,
    state: MutableState<Boolean>,
    modifier: Modifier = Modifier
) {

    val screens = listOf(
        BottomNavBarDestinations.Home,
        BottomNavBarDestinations.Cart,
        BottomNavBarDestinations.Profile,
    )

    NavigationBar(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        windowInsets = WindowInsets.navigationBars
    ) {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }

    }

}

@Composable
fun RowScope.AddItem(
    screen: BottomNavBarDestinations,
    currentDestination: NavDestination?,
    navController: NavHostController
) {

    NavigationBarItem(
        label = {
            Text(text = screen.title, fontFamily = primaryFont())
        },
        alwaysShowLabel = false,
        icon = {
            Icon(
                painter = painterResource(id = screen.icon),
                contentDescription = "screen.title"
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.route
        } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                    saveState = true
                }
                launchSingleTop = true
                restoreState = true
            }
        },
        colors = NavigationBarItemDefaults.colors(
            selectedTextColor = MaterialTheme.colorScheme.onPrimaryContainer,
            selectedIconColor = MaterialTheme.colorScheme.primaryContainer,
            unselectedIconColor = MaterialTheme.colorScheme.onPrimaryContainer,
            indicatorColor = MaterialTheme.colorScheme.onPrimaryContainer
        )
    )

}

@RequiresApi(Build.VERSION_CODES.P)
@Preview
@Composable
fun MainScreenPreview() = FakeStoreComposeTheme {
    MainScreen()
}
