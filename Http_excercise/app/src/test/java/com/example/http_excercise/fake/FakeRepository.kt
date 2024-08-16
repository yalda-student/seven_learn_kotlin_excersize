package com.example.http_excercise.fake

import com.example.http_excercise.data.ProductRepository
import com.example.http_excercise.data.network.response.ProductDetailResponse
import com.example.http_excercise.data.network.response.ProductList

class FakeRepository : ProductRepository{
    override suspend fun getProducts(): ProductList {
       return  ProductList(FakeDataSource.productList);
    }

    override suspend fun getProductDetail(id: String): ProductDetailResponse {
        TODO("Not yet implemented")
    }
}