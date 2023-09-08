package com.jt17.fakestorecompose.data.remote.api

import com.jt17.fakestorecompose.data.remote.dto.StoreProductsResponse
import com.jt17.fakestorecompose.domain.model.Resource
import kotlinx.coroutines.flow.Flow

interface ApiService {

    suspend fun getStoreApi(): List<StoreProductsResponse>

}