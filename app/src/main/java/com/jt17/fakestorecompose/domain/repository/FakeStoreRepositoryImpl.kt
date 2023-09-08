package com.jt17.fakestorecompose.domain.repository

import com.jt17.fakestorecompose.data.local.database.FakeStoreDao
import com.jt17.fakestorecompose.data.local.dto.RemoteStoreProductsDto
import com.jt17.fakestorecompose.data.local.mapper.toProducts
import com.jt17.fakestorecompose.data.mapper.toProducts
import com.jt17.fakestorecompose.data.mapper.toRemoteStoreProductsDto
import com.jt17.fakestorecompose.data.remote.api.ApiService
import com.jt17.fakestorecompose.domain.model.Products
import com.jt17.fakestorecompose.domain.model.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class FakeStoreRepositoryImpl(
    private val api: ApiService,
    private val dao: FakeStoreDao
) : FakeStoreRepository {
    override fun getStoreProducts(): Flow<List<Products>> =
        dao.getProductsList().map { list ->
            list.map { it.toProducts() }
        }

    override suspend fun syncProductsList() {
        val remoteProductsList = api.getStoreApi().map { it.toRemoteStoreProductsDto() }

        dao.upsertProducts(products = remoteProductsList)
    }


}