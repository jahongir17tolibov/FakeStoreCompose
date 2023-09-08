package com.jt17.fakestorecompose.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jt17.fakestorecompose.data.local.dto.LocalStoreProductsDto

@Database(
    entities = [LocalStoreProductsDto::class],
    version = 1
)
abstract class FakeStoreDatabase : RoomDatabase() {

    abstract val storeDao: FakeStoreDao

    companion object {
        const val DATABASE_NAME = "fake_store_db"
    }

}