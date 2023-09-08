package com.jt17.fakestorecompose.domain.use_case

import com.jt17.fakestorecompose.domain.model.Products
import com.jt17.fakestorecompose.domain.model.Resource
import com.jt17.fakestorecompose.domain.repository.FakeStoreRepository
import kotlinx.coroutines.flow.Flow

class SyncStoreProductsUseCase(private val repository: FakeStoreRepository) {

    suspend operator fun invoke() = repository.syncProductsList()


}