package com.example.http_excercise.list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.http_excercise.App
import com.example.http_excercise.data.ProductRepository
import com.example.http_excercise.data.network.response.ProductResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductListViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as App
                val container = application.appContainer
                val repository = container.productRepository
                ProductListViewModel(repository)
            }
        }
    }

    private val _uiState = MutableStateFlow<ProductListUiState>(ProductListUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchProducts()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    val products = repository.getProducts()

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