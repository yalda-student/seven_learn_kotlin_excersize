package  com.example.http_excercise.data

import com.example.http_excercise.data.model.response.ProductDetailResponse
import com.example.http_excercise.data.model.response.ProductList
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Path


interface ApiService {

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://dummyjson.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create()
        }
    }

    @GET("products")
    suspend fun getProducts(): ProductList

    @GET("products/{id}")
    suspend fun getProductById(@Path("id") id: String): ProductDetailResponse

}