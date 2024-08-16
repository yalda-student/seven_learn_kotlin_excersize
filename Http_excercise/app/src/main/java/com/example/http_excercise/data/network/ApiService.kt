package  com.example.http_excercise.data.network

import com.example.http_excercise.data.network.response.ProductDetailResponse
import com.example.http_excercise.data.network.response.ProductList
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("products")
    suspend fun getProducts(): ProductList

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): ProductDetailResponse
}