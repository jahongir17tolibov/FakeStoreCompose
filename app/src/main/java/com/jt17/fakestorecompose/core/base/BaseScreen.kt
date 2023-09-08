package com.jt17.fakestorecompose.core.base

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.EaseInSine
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
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
import androidx.compose.runtime.derivedStateOf
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
import androidx.compose.ui.platform.LocalContext
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
import com.jt17.fakestorecompose.core.navigation.BottomNavBarDestinations
import com.jt17.fakestorecompose.core.navigation.SetupNavHost
import com.jt17.fakestorecompose.core.ui.components.ErrorView
import com.jt17.fakestorecompose.core.ui.components.LoadingView
import com.jt17.fakestorecompose.core.ui.components.primaryFont
import com.jt17.fakestorecompose.core.ui.theme.FakeStoreComposeTheme
import com.jt17.fakestorecompose.presentation.activity.MainActivity
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BaseScreenRoute(
    baseViewModel: BaseViewModel = koinViewModel(),
    content: @Composable () -> Unit
) {

    val (baseState, baseEffect, baseEvent) = useBase(viewModel = baseViewModel)

    val context = LocalContext.current
    val activity = context as? MainActivity

    baseEffect.collectInLaunchedEffect { effect ->
        when (effect) {
            BaseContract.BaseEffect.OnBackPressed -> activity?.onBackPressed()
        }
    }

    BaseScreen(baseState = baseState, content = content)

}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BaseScreen(
    baseState: BaseContract.BaseState,
    content: @Composable () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {

        AnimatedContent(
            targetState = baseState,
            transitionSpec = {
                fadeIn(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = LinearOutSlowInEasing
                    )
                ) togetherWith fadeOut(
                    animationSpec = tween(
                        durationMillis = 500,
                        easing = FastOutLinearInEasing
                    )
                )
            },
            label = "Animated_Contents"
        ) { targetState ->

            when (targetState) {
                BaseContract.BaseState.OnLoading -> LoadingView(modifier = Modifier.fillMaxSize())

                is BaseContract.BaseState.OnError -> ErrorView(errorMessage = targetState.errorMessage)

                BaseContract.BaseState.OnSuccess -> content()
            }

        }

    }

}
