package com.example.http_excercise.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.http_excercise.data.ApiService
import com.example.http_excercise.data.model.response.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductListViewModel : ViewModel() {

    private val _uiState = MutableStateFlow<ProductListUiState>(ProductListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val apiService = ApiService.create()
                    val products = apiService.getProducts()
                    _uiState.value = ProductListUiState.Success(products.products)
                } catch (e: Exception) {
                    println("e: $e")
                    _uiState.value = ProductListUiState.Error
                }
            }
        }
    }

}

sealed interface ProductListUiState {
    data object Loading : ProductListUiState
    data class Success(val products: List<ProductResponse>) : ProductListUiState
    data object Error : ProductListUiState
}