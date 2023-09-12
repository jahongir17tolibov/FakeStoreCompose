package com.jt17.fakestorecompose.core.base

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.jt17.fakestorecompose.core.ui.components.ErrorView
import com.jt17.fakestorecompose.core.ui.components.LoadingView
import com.jt17.fakestorecompose.core.ui.components.ShimmerLoading
import com.jt17.fakestorecompose.presentation.activity.MainActivity
import org.koin.androidx.compose.koinViewModel

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun BaseRoute(
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
                BaseContract.BaseState.OnLoading -> {
                    Column(modifier = Modifier.fillMaxSize()) {

                        repeat(5) {
                            ShimmerLoading()
                        }

                    }
                }

                is BaseContract.BaseState.OnError -> ErrorView(errorMessage = targetState.errorMessage)

                BaseContract.BaseState.OnSuccess -> content()
            }

        }

    }

}
