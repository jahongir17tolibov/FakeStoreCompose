package com.jt17.fakestorecompose.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Upsert
import com.jt17.fakestorecompose.data.local.dto.LocalStoreProductsDto
import com.jt17.fakestorecompose.data.local.dto.RemoteStoreProductsDto
import com.jt17.fakestorecompose.domain.model.Products
import kotlinx.coroutines.flow.Flow

@Dao
interface FakeStoreDao {

    @Query("SELECT * FROM store_products ORDER BY id ASC")
    fun getProductsList(): Flow<List<LocalStoreProductsDto>>

    @Query("SELECT * FROM store_products WHERE isFavourite ORDER BY title ASC")
    fun getFavouriteProductsList(): Flow<List<LocalStoreProductsDto>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProductsList(localStore: LocalStoreProductsDto)

    @Upsert(entity = LocalStoreProductsDto::class)
    suspend fun upsertProducts(products: List<RemoteStoreProductsDto>)

}