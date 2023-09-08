package com.jt17.fakestorecompose.data.local.mapper

import com.jt17.fakestorecompose.data.local.dto.LocalStoreProductsDto
import com.jt17.fakestorecompose.domain.model.Products

fun LocalStoreProductsDto.toProducts(): Products = Products(
    id = id,
    title = title,
    price = price,
    category = category,
    description = description,
    image = image,
    rate = rate,
    count = count,
    isFavourite = isFavourite
)

fun Products.toLocalStoreProductsDto(): LocalStoreProductsDto = LocalStoreProductsDto(
    id = id,
    title = title,
    price = price,
    category = category,
    description = description,
    image = image,
    rate = rate,
    count = count,
    isFavourite = isFavourite
)