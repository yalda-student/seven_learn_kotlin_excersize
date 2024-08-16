package com.example.http_excercise.detail

import com.example.http_excercise.fake.FakeDataSource
import com.example.http_excercise.fake.FakeRepository
import com.example.http_excercise.list.ProductListUiState
import com.example.http_excercise.list.ProductListViewModel
import com.example.http_excercise.rule.TestDispatcherRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

class ProductDetailViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun `getProductDetail verify ui state success`() {
        val viewModel = ProductDetailViewModel(FakeRepository())
        viewModel.fetchProductData("")
        val fakeDetail = FakeDataSource.productDetail
        val expected = ProductDetailUiState.ProductData(
            id = fakeDetail.id,
            name = fakeDetail.title,
            description = fakeDetail.description,
            brand = fakeDetail.brand,
            thumbnail = fakeDetail.thumbnail,
            category = fakeDetail.category,
            rating = fakeDetail.rating,
            price = fakeDetail.price

        )
        assertEquals(
            expected,
            viewModel.uiState.value
        )
    }
}