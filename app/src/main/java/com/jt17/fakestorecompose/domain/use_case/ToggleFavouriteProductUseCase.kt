package com.jt17.fakestorecompose.domain.use_case

import com.jt17.fakestorecompose.domain.model.Products
import com.jt17.fakestorecompose.domain.repository.FakeStoreRepository

class ToggleFavouriteProductUseCase(private val repository: FakeStoreRepository) {

    suspend operator fun invoke(product: Products) = repository.toggleFavouriteProducts(product)

}