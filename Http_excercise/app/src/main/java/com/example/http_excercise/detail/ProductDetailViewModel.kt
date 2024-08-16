package com.example.http_excercise.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.http_excercise.data.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()
    fun fetchProductData(productId: String) {
        viewModelScope.launch {
            val apiService = ApiService.create()

            val response = apiService.getProductById(productId)
            val state = ProductDetailUiState.ProductData(
                name = response.title,
                rating = response.rating.toString(),
                description = response.description,
                brand = response.brand,
                category = response.category,
                id = response.id,
                price = response.price,
                thumbnail = response.thumbnail
            )
            _uiState.value = state
        }
    }

}

sealed interface ProductDetailUiState {
    data object Loading : ProductDetailUiState
    data class ProductData(
        val id: Int,
        val name: String,
        val rating: String,
        val description: String,
        val thumbnail: String,
        val brand: String,
        val category: String,
        val price: Double,
    ) : ProductDetailUiState
}