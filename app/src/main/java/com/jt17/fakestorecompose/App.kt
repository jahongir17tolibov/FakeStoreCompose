package com.jt17.fakestorecompose

import android.app.Application
import com.jt17.fakestorecompose.data.di.appModule
import com.jt17.fakestorecompose.data.di.dataModule
import com.jt17.fakestorecompose.data.di.networkModule
import com.jt17.fakestorecompose.data.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent
import org.koin.core.context.startKoin

class App : Application(), KoinComponent {

    companion object {
        private lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this

        val koinModules = listOf(
            appModule,
            dataModule,
            networkModule,
            viewModelModule,
        )

        startKoin {
            androidLogger()
            androidContext(androidContext = instance)
            modules(koinModules)
        }

    }
}