package com.example.http_excercise.list

import com.example.http_excercise.fake.FakeDataSource
import com.example.http_excercise.fake.FakeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)

class ProductListViewModelTest {

    @Before
    fun setup() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
    }

    @Test
    fun `getProductList verify ui state success`() {
        val viewModel = ProductListViewModel(FakeRepository())
        assertEquals(
            ProductListUiState.Success(FakeDataSource.productList),
            viewModel.uiState.value
        )
    }

    @After
    fun release() {
        Dispatchers.resetMain()
    }
}