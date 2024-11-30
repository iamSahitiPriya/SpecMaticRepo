package com.store.services

import com.store.models.Product
import com.store.models.ProductRequest
import com.store.models.ProductType
import com.store.repositories.ProductRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class ProductService(
    @Autowired val productRepository: ProductRepository
) {

    fun getProducts(type: ProductType): List<Product> {
        return productRepository.findByType(type).stream().collect(Collectors.toList())
    }

    fun getAllProducts(): List<Product> {
        return productRepository.findAll().stream().collect(Collectors.toList())
    }

    fun create(productRequest: ProductRequest): Product {
        return productRepository.save(productRequest)
    }

}

