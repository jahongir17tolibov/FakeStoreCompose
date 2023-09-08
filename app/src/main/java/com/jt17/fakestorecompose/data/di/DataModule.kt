package com.jt17.fakestorecompose.data.di

import androidx.room.Room
import com.jt17.fakestorecompose.data.local.database.FakeStoreDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single {
        Room.databaseBuilder(
            context = androidApplication(),
            klass = FakeStoreDatabase::class.java,
            name = FakeStoreDatabase.DATABASE_NAME,
        )
    }

    single { get<FakeStoreDatabase>().storeDao }

}