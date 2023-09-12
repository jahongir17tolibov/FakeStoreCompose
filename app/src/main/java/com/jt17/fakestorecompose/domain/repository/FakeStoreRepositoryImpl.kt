package com.jt17.fakestorecompose.domain.repository

import com.jt17.fakestorecompose.data.local.database.FakeStoreDao
import com.jt17.fakestorecompose.data.local.mapper.toLocalStoreProductsDto
import com.jt17.fakestorecompose.data.local.mapper.toProducts
import com.jt17.fakestorecompose.data.mapper.toLogin
import com.jt17.fakestorecompose.data.mapper.toRemoteStoreProductsDto
import com.jt17.fakestorecompose.data.remote.api.ApiService
import com.jt17.fakestorecompose.data.remote.dto.LoginResponse
import com.jt17.fakestorecompose.domain.model.Login
import com.jt17.fakestorecompose.domain.model.Products
import com.jt17.fakestorecompose.domain.model.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
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

    override fun getFavouriteProductsList(): Flow<List<Products>> =
        dao.getFavouriteProductsList().map { list -> list.map { it.toProducts() } }

    override suspend fun syncProductsList() {
        val remoteProductsList = api.getStoreApi().map { it.toRemoteStoreProductsDto() }

        dao.upsertProducts(products = remoteProductsList)
    }

    override suspend fun toggleFavouriteProducts(oldProducts: Products) {
        val product =
            oldProducts.toLocalStoreProductsDto().copy(isFavourite = oldProducts.isFavourite.not())
        dao.insertProductsList(product)
    }

    override suspend fun loginToStore(username: String, password: String): Flow<Resource<Login>> =
        flow {
            try {
                val request = api.loginToStore(username = username, password = password).toLogin()
                emit(value = Resource.Success(request))
            } catch (e: Exception) {
                emit(Resource.Error(exception = e))
            }
        }.flowOn(IO)

}