package com.jt17.fakestorecompose.core.base

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest

//state effect dispatch
data class StateEffectDispatch<STATE, EFFECT, EVENT>(
    val state: STATE,
    val effectFlow: Flow<EFFECT>,
    val dispatch: (EVENT) -> Unit,
)

//state dispatch
data class StateDispatch<STATE, EVENT>(
    val state: STATE,
    val dispatch: (EVENT) -> Unit,
)

//use function for viewModels
@Composable
inline fun <reified STATE, EVENT> use(
    viewModel: UnidirectionalViewModel<STATE, EVENT>,
): StateDispatch<STATE, EVENT> {
    val state by viewModel.state.collectAsStateWithLifecycle()

    val dispatch: (EVENT) -> Unit = { event: EVENT ->
        viewModel.event(event)
    }
    return StateDispatch(
        state = state,
        dispatch = dispatch
    )
}

//useBase function for BaseViewModel
@Composable
inline fun <reified BASE_STATE, BASE_EFFECT, BASE_EVENT> useBase(
    viewModel: UnidirectionalBaseViewModel<BASE_STATE, BASE_EFFECT, BASE_EVENT>,
): StateEffectDispatch<BASE_STATE, BASE_EFFECT, BASE_EVENT> {
    val state by viewModel.baseState.collectAsStateWithLifecycle()

    val dispatch: (BASE_EVENT) -> Unit = { baseEvent ->
        viewModel.baseEvent(event = baseEvent)
    }

    return StateEffectDispatch(
        state = state,
        effectFlow = viewModel.baseEffect,
        dispatch = dispatch
    )

}

//Unidirectional viewModel for extending
interface UnidirectionalViewModel<STATE, EVENT> {
    val state: StateFlow<STATE>
    fun event(event: EVENT)
}

//Unidirectional viewModel for BaseViewModel
interface UnidirectionalBaseViewModel<BASE_STATE, BASE_EFFECT, BASE_EVENT> {
    val baseState: StateFlow<BASE_STATE>
    val baseEffect: Flow<BASE_EFFECT>
    fun baseEvent(event: BASE_EVENT)
}

//collect in launched effect
@SuppressLint("ComposableNaming")
@Composable
fun <T> Flow<T>.collectInLaunchedEffect(function: suspend (value: T) -> Unit) {
    val flow: Flow<T> = this
    LaunchedEffect(key1 = flow) {
        flow.collectLatest(function)
    }

}