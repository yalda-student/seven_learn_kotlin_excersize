package com.example.http_excercise.fake

import com.example.http_excercise.data.network.ApiService
import com.example.http_excercise.data.network.response.ProductDetailResponse
import com.example.http_excercise.data.network.response.ProductList

class FakeApiService: ApiService {
    override suspend fun getProducts(): ProductList {
        return  ProductList(FakeDataSource.productList);
    }

    override suspend fun getProductById(id: String): ProductDetailResponse {
        TODO("Not yet implemented")
    }
}