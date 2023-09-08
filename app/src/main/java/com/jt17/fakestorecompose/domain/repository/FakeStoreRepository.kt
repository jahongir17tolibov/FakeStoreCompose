package com.jt17.fakestorecompose.domain.repository

import com.jt17.fakestorecompose.data.local.dto.RemoteStoreProductsDto
import com.jt17.fakestorecompose.domain.model.Products
import com.jt17.fakestorecompose.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface FakeStoreRepository {

    fun getStoreProducts(): Flow<List<Products>>

    suspend fun syncProductsList()

}