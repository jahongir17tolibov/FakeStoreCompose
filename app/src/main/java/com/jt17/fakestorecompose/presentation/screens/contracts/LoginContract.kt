package com.jt17.fakestorecompose.presentation.screens.contracts

import com.jt17.fakestorecompose.core.base.UnidirectionalViewModel
import com.jt17.fakestorecompose.data.remote.dto.LoginUserData
import com.jt17.fakestorecompose.domain.model.Login

interface LoginContract : UnidirectionalViewModel<LoginContract.State, LoginContract.Event> {

    data class State(
        val onGetLoginToken: Login = Login(),
        val loading: Boolean = false,
    )

    sealed interface Event {
        data object OnGetLoginToken : Event
        data object OnLoading : Event
        data class SetLoginUserData(val loginUserData: LoginUserData) : Event
    }

}