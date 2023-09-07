package com.jt17.fakestorecompose

import android.app.Application
import org.koin.core.component.KoinComponent

class App : Application(), KoinComponent {

    companion object {
        private lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        instance = this


    }
}