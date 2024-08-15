package com.example.http_excercise.data

import com.example.http_excercise.data.network.ApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface AppContainer {
    val productRepository: ProductRepository
}

class DefaultAppContainer : AppContainer {
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("https://dummyjson.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val apiService: ApiService by lazy {
        retrofit.create()
    }

    override val productRepository: ProductRepository by lazy { ProductRepositoryImpl(apiService) }

}