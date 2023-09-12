package com.jt17.fakestorecompose.data.di

import com.jt17.fakestorecompose.core.base.BaseViewModel
import com.jt17.fakestorecompose.domain.use_case.GetStoreProductsUseCase
import com.jt17.fakestorecompose.presentation.viewmodel.HomeViewModel
import com.jt17.fakestorecompose.presentation.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::BaseViewModel)

    viewModel { HomeViewModel(get(), get(), get(), get()) }

    viewModel { LoginViewModel(get(), get()) }

}