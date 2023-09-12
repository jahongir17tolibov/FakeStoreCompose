package com.jt17.fakestorecompose.domain.use_case

import com.jt17.fakestorecompose.domain.model.Products
import com.jt17.fakestorecompose.domain.repository.FakeStoreRepository
import kotlinx.coroutines.flow.Flow

class GetFavouriteProductsListUseCase(private val repository: FakeStoreRepository) {

    operator fun invoke(): Flow<List<Products>> = repository.getFavouriteProductsList()

}