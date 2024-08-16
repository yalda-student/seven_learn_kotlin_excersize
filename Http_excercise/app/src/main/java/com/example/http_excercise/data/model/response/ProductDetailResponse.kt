package com.example.http_excercise.data.model.response

data class ProductDetailResponse(
    val id: Int,
    val title: String,
    val rating: Double,
    val price: Double,
    val description: String,
    val brand: String,
    val category: String,
    val thumbnail: String,
)
