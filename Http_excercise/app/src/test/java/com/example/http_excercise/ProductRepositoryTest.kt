package com.example.http_excercise

import com.example.http_excercise.data.ProductRepositoryImpl
import com.example.http_excercise.fake.FakeApiService
import com.example.http_excercise.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class ProductRepositoryTest {

    @Test
    fun `getAllProducts should return all added products`() = runTest {
        val repository = ProductRepositoryImpl(FakeApiService())
        assertEquals(FakeDataSource.productList, repository.getProducts().products)
    }

    @Test
    fun `getProductById should return null for non-existent user`() = runTest {
        val repository = ProductRepositoryImpl(FakeApiService())

        val result = repository.getProductDetail("1")
        assertNull(result)
    }
}