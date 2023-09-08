package com.jt17.fakestorecompose.domain.model

import androidx.compose.runtime.Immutable
import kotlinx.serialization.Serializable

@Immutable
@Serializable
data class Products(
    val id: Int,
    val title: String,
    val price: Double,
    val description: String,
    val category: String,
    val image: String,
    val rate: Double,
    val count: Int,
    var isFavourite: Boolean = false
)
