package com.jt17.fakestorecompose.presentation.activity

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.core.navigation.BottomNavBarDestinations
import com.jt17.fakestorecompose.core.navigation.SetupNavHost
import com.jt17.fakestorecompose.core.ui.components.primaryFont
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme

class MainActivity : ComponentActivity() {

    private lateinit var navController: NavHostController

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            FakeStoreComposeTheme {

                var baseViewModel: BaseViewModel by remember { mutableStateOf(BaseViewModel()) }

                val bottomBarHeight = 56.dp

                navController = rememberNavController()
                val bottomBarOffsetHeightPx = remember { mutableFloatStateOf(0f) }
                val buttonVisible = remember { mutableStateOf(true) }

                LaunchedEffect(bottomBarOffsetHeightPx.floatValue) {
                    buttonVisible.value = bottomBarOffsetHeightPx.floatValue >= -5
                }

                //base of screens
                Scaffold(
                    modifier = Modifier.bottomBarAnimatedScroll(
                        height = bottomBarHeight,
                        offsetHeightPx = bottomBarOffsetHeightPx
                    ),
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

                        SetupNavHost(
                            navController = navController,
                            modifier = Modifier.padding(paddingValues.calculateBottomPadding()),
                            onProvideBaseViewModel = { viewModel ->
                                baseViewModel = viewModel
                            }
                        )

                    }

                }

            }
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

    AnimatedVisibility(
        visible = state.value,
        enter = slideInVertically(
            initialOffsetY = { it },
            animationSpec = tween(durationMillis = 100, easing = FastOutSlowInEasing)
        ),
        exit = slideOutVertically(
            targetOffsetY = { it },
            animationSpec = tween(durationMillis = 100, easing = FastOutLinearInEasing)
        )
    ) {

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

fun Modifier.bottomBarAnimatedScroll(
    height: Dp = 56.dp, offsetHeightPx: MutableFloatState
): Modifier = composed {

    val bottomBarHeightPx = with(LocalDensity.current) {
        height.roundToPx().toFloat()
    }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                val delta = available.y
                val newOffset = offsetHeightPx.floatValue + delta
                offsetHeightPx.floatValue = newOffset.coerceIn(
                    minimumValue = -bottomBarHeightPx,
                    maximumValue = 0f
                )

                return Offset.Zero
            }
        }
    }

    this.nestedScroll(nestedScrollConnection)
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier,
        color = MaterialTheme.colorScheme.primary
    )
}

@RequiresApi(Build.VERSION_CODES.P)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun GreetingPreview() {
    FakeStoreComposeTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            Greeting("Android")
        }
    }
}