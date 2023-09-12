package com.jt17.fakestorecompose.presentation.viewmodel

import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.core.dispatcher.DispatcherProvider
import com.jt17.fakestorecompose.domain.use_case.LoginToStoreUseCase
import com.jt17.fakestorecompose.presentation.screens.contracts.LoginContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel(
    private val loginToStoreUseCase: LoginToStoreUseCase,
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel(dispatcherProvider = dispatcherProvider), LoginContract {

    private val mutableState = MutableStateFlow(LoginContract.State())
    override val state: StateFlow<LoginContract.State> get() = mutableState.asStateFlow()

    override fun event(event: LoginContract.Event) {
        TODO("Not yet implemented")
    }
}