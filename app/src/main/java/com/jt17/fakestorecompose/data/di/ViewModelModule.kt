package com.jt17.fakestorecompose.data.di

import com.jt17.fakestorecompose.core.base.BaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {

    viewModelOf(::BaseViewModel)

}