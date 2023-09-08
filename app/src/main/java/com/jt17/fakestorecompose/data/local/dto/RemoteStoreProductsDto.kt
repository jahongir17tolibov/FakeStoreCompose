package com.jt17.fakestorecompose.data.local.dto

import kotlinx.serialization.Serializable

data class RemoteStoreProductsDto(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rate: Double,
    val count: Int,
)