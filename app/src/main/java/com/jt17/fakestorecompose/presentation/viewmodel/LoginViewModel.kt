package com.jt17.fakestorecompose.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.jt17.fakestorecompose.core.base.BaseContract
import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.core.dispatcher.DispatcherProvider
import com.jt17.fakestorecompose.data.remote.dto.LoginUserData
import com.jt17.fakestorecompose.domain.model.Resource
import com.jt17.fakestorecompose.domain.use_case.LoginToStoreUseCase
import com.jt17.fakestorecompose.presentation.screens.contracts.LoginContract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(
    private val loginToStoreUseCase: LoginToStoreUseCase,
    dispatcherProvider: DispatcherProvider,
) : BaseViewModel(dispatcherProvider = dispatcherProvider), LoginContract {

    private val mutableState = MutableStateFlow(LoginContract.State())
    override val state: StateFlow<LoginContract.State> get() = mutableState.asStateFlow()

    override fun event(event: LoginContract.Event) = when (event) {
        is LoginContract.Event.SetLoginUserData -> getLoginToken(loginUserData = event.loginUserData)
    }

    private fun getLoginToken(loginUserData: LoginUserData, isLoading: Boolean = false) {
        loginToStoreUseCase(username = loginUserData.username, password = loginUserData.password)
            .onEach { result ->
                when (result) {
                    is Resource.Success -> {
                        result.data?.let { login ->
                            if (isLoading.not()) {
                                mutableBaseState.update { BaseContract.BaseState.OnSuccess }
                            } else {
                                mutableState.update {
                                    it.copy(loading = false)
                                }
                            }
                            mutableState.update {
                                it.copy(onGetLoginToken = login)
                            }
                        }
                    }

                    is Resource.Error -> {
                        mutableBaseState.update {
                            BaseContract.BaseState.OnError(
                                errorMessage = result.exception?.localizedMessage
                                    ?: "An unexpected error occurred."
                            )
                        }
                    }
                }
            }.catch { exception ->
                mutableBaseState.update {
                    BaseContract.BaseState.OnError(
                        errorMessage = exception.localizedMessage ?: "An unexpected error occurred."
                    )
                }
            }.launchIn(viewModelScope)
    }
}