package com.jt17.fakestorecompose.core.base

interface BaseContract :
    UnidirectionalBaseViewModel<BaseContract.BaseState, BaseContract.BaseEffect, BaseContract.BaseEvent> {

    sealed interface BaseState {

        data object OnLoading : BaseState
        data class OnError(val errorMessage: String) : BaseState
        data object OnSuccess : BaseState

    }

    sealed interface BaseEffect {
        data object OnBackPressed : BaseEffect
    }

    sealed interface BaseEvent {
        data object OnBackPressed : BaseEvent
        data object OnRetryPressed : BaseEvent
    }

}