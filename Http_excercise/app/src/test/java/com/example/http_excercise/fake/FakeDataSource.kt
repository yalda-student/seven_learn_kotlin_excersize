package com.example.http_excercise.fake

import com.example.http_excercise.data.network.response.ProductResponse

object FakeDataSource {
    val productList = listOf(
        ProductResponse(
            id = "1",
            title = "Essence Mascara Lash Princess",
            thumbnail = "",
            price = 9.99,
            category = "beauty"
        ),
        ProductResponse(
            id = "2",
            title = "Eyeshadow Palette with Mirror",
            thumbnail = "",
            price = 19.99,
            category = "beauty"
        ),
        ProductResponse(
            id = "3",
            title = "Powder Canister",
            thumbnail = "",
            price = 19.99,
            category = "beauty"
        ),
    )
}