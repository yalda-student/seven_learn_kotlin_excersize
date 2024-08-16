package  com.example.http_excercise.data

import com.example.http_excercise.data.network.ApiService
import com.example.http_excercise.data.network.response.ProductDetailResponse
import com.example.http_excercise.data.network.response.ProductList


interface ProductRepository {
    suspend fun getProducts(): ProductList
    suspend fun getProductDetail(id: String): ProductDetailResponse
}

class ProductRepositoryImpl(private val apiService: ApiService) : ProductRepository {
    override suspend fun getProducts(): ProductList{
        return apiService.getProducts()
    }

    override suspend fun getProductDetail(id: String): ProductDetailResponse {
        return apiService.getProductById(id)
    }

}