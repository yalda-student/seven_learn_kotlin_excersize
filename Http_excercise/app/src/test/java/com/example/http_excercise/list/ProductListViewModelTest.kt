package com.example.http_excercise.list

import com.example.http_excercise.fake.FakeDataSource
import com.example.http_excercise.fake.FakeRepository
import com.example.http_excercise.rule.TestDispatcherRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


class ProductListViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun `getProductList verify ui state success`() {
        val viewModel = ProductListViewModel(FakeRepository())
        assertEquals(
            ProductListUiState.Success(FakeDataSource.productList),
            viewModel.uiState.value
        )
    }


}