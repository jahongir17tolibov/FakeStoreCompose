package com.jt17.fakestorecompose.data.remote.api

import com.jt17.fakestorecompose.data.remote.dto.StoreProductsResponse
import com.jt17.fakestorecompose.domain.model.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.get
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.json.Json

class FakeStoreApi(private val httpClient: HttpClient) : ApiService {

    companion object {
        private const val BASE_URL = "https://fakestoreapi.com"
    }

    override suspend fun getStoreApi(): List<StoreProductsResponse> = try {
        val response = httpClient.get("$BASE_URL/products").body<String>()
        val products = Json.decodeFromString<List<StoreProductsResponse>>(response)
        //return
        products

    } catch (e: ResponseException) {
        emptyList()
    }

}