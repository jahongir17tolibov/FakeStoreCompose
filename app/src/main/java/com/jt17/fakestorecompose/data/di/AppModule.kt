package com.jt17.fakestorecompose.data.di

import com.jt17.fakestorecompose.core.dispatcher.DispatcherProvider
import com.jt17.fakestorecompose.core.dispatcher.PlatformDispatcherProvider
import com.jt17.fakestorecompose.domain.repository.FakeStoreRepository
import com.jt17.fakestorecompose.domain.repository.FakeStoreRepositoryImpl
import com.jt17.fakestorecompose.domain.use_case.GetFavouriteProductsListUseCase
import com.jt17.fakestorecompose.domain.use_case.SyncStoreProductsUseCase
import com.jt17.fakestorecompose.domain.use_case.GetStoreProductsUseCase
import com.jt17.fakestorecompose.domain.use_case.ToggleFavouriteProductUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {

    factory { CoroutineScope(Dispatchers.Main + SupervisorJob()) }

    single { PlatformDispatcherProvider() } bind DispatcherProvider::class

    single { FakeStoreRepositoryImpl(get(), get()) } bind FakeStoreRepository::class

    single { GetStoreProductsUseCase(get()) }

    single { SyncStoreProductsUseCase(get()) }

    single { ToggleFavouriteProductUseCase(get()) }

    single { GetFavouriteProductsListUseCase(get()) }

}