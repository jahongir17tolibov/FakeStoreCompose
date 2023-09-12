package com.jt17.fakestorecompose.core.base

import androidx.lifecycle.ViewModel
import com.jt17.fakestorecompose.core.dispatcher.DispatcherProvider
import com.jt17.fakestorecompose.core.dispatcher.PlatformDispatcherProvider
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.withContext

open class BaseViewModel(
    protected val dispatcherProvider: DispatcherProvider = PlatformDispatcherProvider()
) : ViewModel(), BaseContract {

    protected val mutableBaseState: MutableStateFlow<BaseContract.BaseState> =
        MutableStateFlow(BaseContract.BaseState.OnSuccess)
    override val baseState: StateFlow<BaseContract.BaseState>
        get() = mutableBaseState.asStateFlow()

    private val baseEffectChannel = Channel<BaseContract.BaseEffect>(Channel.UNLIMITED)
    override val baseEffect: Flow<BaseContract.BaseEffect>
        get() = baseEffectChannel.receiveAsFlow()

    override fun baseEvent(event: BaseContract.BaseEvent) = when (event) {
        BaseContract.BaseEvent.OnBackPressed -> onBackPressed()
        BaseContract.BaseEvent.OnRetryPressed -> onRetryPressed()
    }

    private fun onBackPressed() {
        baseEffectChannel.trySend(BaseContract.BaseEffect.OnBackPressed)
    }

    private fun onRetryPressed() { /* keyin bir nima o'ylab topaman :) */
    }

    protected suspend inline fun <T> onUI(crossinline action: suspend () -> T): T {
        return withContext(dispatcherProvider.ui) {
            action()
        }
    }

    protected suspend inline fun <T> onIO(crossinline action: suspend () -> T): T {
        return withContext(dispatcherProvider.io) {
            action()
        }
    }

    protected suspend inline fun <T> onBG(crossinline action: suspend () -> T): T {
        return withContext(dispatcherProvider.bg) {
            action()
        }
    }

}