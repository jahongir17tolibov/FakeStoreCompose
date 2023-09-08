package com.jt17.fakestorecompose.data.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "store_products", primaryKeys = ["id"])
data class LocalStoreProductsDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rate: Double,
    val count: Int,
    @ColumnInfo(defaultValue = "false") var isFavourite: Boolean,
)
