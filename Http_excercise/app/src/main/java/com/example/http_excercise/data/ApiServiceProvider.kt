package com.example.http_excercise.data


object ApiServiceProvider {

    val apiService: ApiService by lazy { ApiService.create() }

}