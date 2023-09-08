package com.jt17.fakestorecompose.data.mapper

import com.jt17.fakestorecompose.data.local.dto.RemoteStoreProductsDto
import com.jt17.fakestorecompose.data.remote.dto.StoreProductsResponse
import com.jt17.fakestorecompose.domain.model.Products

fun StoreProductsResponse.toRemoteStoreProductsDto(): RemoteStoreProductsDto =
    RemoteStoreProductsDto(
        id = id,
        title = title,
        price = price,
        category = category,
        description = description,
        image = image,
        rate = rating.rate,
        count = rating.count,
    )

fun StoreProductsResponse.toProducts(): Products = Products(
    id = id,
    title = title,
    price = price,
    category = category,
    description = description,
    image = image,
    rate = rating.rate,
    count = rating.count,
)

