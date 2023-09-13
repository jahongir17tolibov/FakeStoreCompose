package com.jt17.fakestorecompose.domain.repository

import com.jt17.fakestorecompose.data.local.dto.LocalStoreProductsDto
import com.jt17.fakestorecompose.data.local.dto.RemoteStoreProductsDto
import com.jt17.fakestorecompose.data.remote.dto.LoginResponse
import com.jt17.fakestorecompose.domain.model.Login
import com.jt17.fakestorecompose.domain.model.Products
import com.jt17.fakestorecompose.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface FakeStoreRepository {

    fun getStoreProducts(): Flow<List<Products>>

    fun getFavouriteProductsList(): Flow<List<Products>>

    suspend fun syncProductsList()

    suspend fun toggleFavouriteProducts(oldProducts: Products)

    fun loginToStore(username: String, password: String): Flow<Resource<Login>>

}