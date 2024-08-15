package com.example.http_excercise.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.http_excercise.App
import com.example.http_excercise.data.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
    private val repository: ProductRepository,
) : ViewModel() {

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App
                val container = application.appContainer
                val repository = container.productRepository
                ProductDetailViewModel(repository)
            }
        }
    }

    private val _uiState = MutableStateFlow<ProductDetailUiState>(ProductDetailUiState.Loading)
    val uiState = _uiState.asStateFlow()
    fun fetchProductData(coinId: String) {
        viewModelScope.launch {
            val response = repository.getCoinDetail(coinId)
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