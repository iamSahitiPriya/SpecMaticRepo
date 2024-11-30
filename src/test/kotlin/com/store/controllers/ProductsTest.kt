package com.store.controllers

import com.store.models.Product
import com.store.models.ProductRequest
import com.store.models.ProductResponse
import com.store.models.ProductType
import com.store.services.ProductService
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ProductsTest {
    private val productService = mockk<ProductService>()

    @Test
    fun `should create the product when request is valid`() {
        every {
            productService.create(any())
        } returns Product(1, "Phone", ProductType.gadget, 2, 200)
        val productController = Products(productService)
        val productRequest = ProductRequest(name = "Phone", type = ProductType.gadget, inventory = 2, cost = 1230)

        val actualResponse = productController.createProduct(productRequest)

        assertEquals(actualResponse.body, ProductResponse(1))
    }

    @Test
    fun `should fetch the product based on type when type when is given`() {
        every {
            productService.getProducts(any())
        } returns listOf(Product(1, "Phone", ProductType.gadget, 2, 100))
        val productController = Products(productService)

        val actualResponse = productController.getProducts(ProductType.gadget)

        assertEquals(actualResponse.statusCode.value(), 200)
    }

    @Test
    fun `should fetch all the products when type is not given`() {
        every {
            productService.getAllProducts()
        } returns listOf(Product(1, "Phone", ProductType.gadget, 2, 100))
        val productController = Products(productService)

        val actualResponse = productController.getProducts(null)

        assertEquals(actualResponse.statusCode.value(), 200)
    }
}