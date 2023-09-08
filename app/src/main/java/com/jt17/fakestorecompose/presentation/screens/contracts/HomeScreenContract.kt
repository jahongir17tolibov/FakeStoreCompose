package com.jt17.fakestorecompose.presentation.screens.contracts

import com.jt17.fakestorecompose.core.base.UnidirectionalViewModel
import com.jt17.fakestorecompose.domain.model.Products
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

interface HomeScreenContract :
    UnidirectionalViewModel<HomeScreenContract.State, HomeScreenContract.Event> {

    data class State(
        val productsList: PersistentList<Products> = persistentListOf(),
        val onLoading: Boolean = false
    )

    sealed interface Event {
        data object OnGetProductsList : Event
        data object OnLoading : Event
    }

}

