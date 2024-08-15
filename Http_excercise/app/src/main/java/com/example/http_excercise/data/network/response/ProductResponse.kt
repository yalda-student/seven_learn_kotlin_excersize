package com.example.http_excercise.data.network.response

data class ProductResponse(
    val id: String,
    val title: String,
    val category: String,
    val thumbnail: String,
    val price: Double,
)

data class ProductList(val products: List<ProductResponse>)