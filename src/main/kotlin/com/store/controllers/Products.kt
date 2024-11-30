package com.store.controllers

import com.store.models.Product
import com.store.models.ProductRequest
import com.store.models.ProductResponse
import com.store.models.ProductType
import com.store.services.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/products")
class Products(
    @Autowired val productService: ProductService,
) {
    @GetMapping
    fun getProducts(
        @RequestParam(required = false) type: ProductType?,
    ): ResponseEntity<List<Product>> {
        return if (type == null) {
            ResponseEntity.ok(productService.getAllProducts())
        } else {
            ResponseEntity.ok(productService.getProducts(type))
        }
    }

    @PostMapping
    fun createProduct(@RequestBody productRequest: ProductRequest): ResponseEntity<ProductResponse> {
        val productId = productService.create(productRequest).id
        return ResponseEntity.status(201).body(ProductResponse(productId))
    }
}