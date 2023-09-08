package com.jt17.fakestorecompose.presentation.viewmodel

import androidx.lifecycle.viewModelScope
import com.jt17.fakestorecompose.core.base.BaseContract
import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.core.dispatcher.DispatcherProvider
import com.jt17.fakestorecompose.domain.model.Resource
import com.jt17.fakestorecompose.domain.use_case.SyncStoreProductsUseCase
import com.jt17.fakestorecompose.domain.use_case.GetStoreProductsUseCase
import com.jt17.fakestorecompose.presentation.screens.contracts.HomeScreenContract
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getStoreProductsUseCase: GetStoreProductsUseCase,
    private val syncStoreProductsUseCase: SyncStoreProductsUseCase,
    dispatcherProvider: DispatcherProvider
) : BaseViewModel(dispatcherProvider), HomeScreenContract {

    private val mutableState = MutableStateFlow(HomeScreenContract.State())
    override val state: StateFlow<HomeScreenContract.State> get() = mutableState.asStateFlow()

    override fun event(event: HomeScreenContract.Event) = when (event) {
        HomeScreenContract.Event.OnGetProductsList -> getData()
        HomeScreenContract.Event.OnLoading -> getData(isLoading = true)
    }

    private fun getData(isLoading: Boolean = false) {
        if (isLoading) {
            mutableState.update {
                it.copy(onLoading = true)
            }
        }

        viewModelScope.launch {
            getProductsList()
        }

    }

    private suspend fun getProductsList() {
        mutableBaseState.update { BaseContract.BaseState.OnLoading }
        //fetching data from api and upsert to database won't show to ui
        try {
            syncStoreProductsUseCase()
            mutableBaseState.update { BaseContract.BaseState.OnSuccess }
        } catch (e: Exception) {
            mutableBaseState.update {
                BaseContract.BaseState.OnError(
                    errorMessage = e.localizedMessage ?: "An unexpected error occurred."
                )
            }
        }
        //get products list from database will show to ui
        getStoreProductsUseCase()
            .onEach { result ->
                mutableState.update {
                    it.copy(productsList = result.toPersistentList())
                }
                mutableBaseState.update { BaseContract.BaseState.OnSuccess }
            }.catch { exception ->
                mutableBaseState.update {
                    BaseContract.BaseState.OnError(
                        errorMessage = exception.localizedMessage
                            ?: "An unexpected error occurred."
                    )
                }
            }.launchIn(viewModelScope)
    }

}