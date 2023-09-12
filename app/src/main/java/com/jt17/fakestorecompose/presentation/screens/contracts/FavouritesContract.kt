package com.jt17.fakestorecompose.presentation.screens.contracts

import com.jt17.fakestorecompose.core.base.UnidirectionalViewModel
import com.jt17.fakestorecompose.domain.model.Products
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

interface FavouritesContract :
    UnidirectionalViewModel<FavouritesContract.State, FavouritesContract.Event> {

    data class State(
        val favouritesList: PersistentList<Products> = persistentListOf(),
        val onLoading: Boolean = false
    )

    sealed interface Event {
        data object OnGetFavouritesList : Event
        data object OnLoading : Event
        data class OnDeleteFavouriteProduct(val product: Products)
        data object OnClearAllFavourites : Event
    }


}