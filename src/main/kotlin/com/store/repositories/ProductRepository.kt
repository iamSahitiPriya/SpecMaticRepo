package com.store.repositories

import com.store.models.Product
import com.store.models.ProductRequest
import com.store.models.ProductType
import org.springframework.stereotype.Repository

@Repository
class ProductRepository {


    private val memoryMap: MutableMap<ProductType, MutableList<Product>> = mutableMapOf(
        ProductType.book to mutableListOf(Product(1, "java", ProductType.book, 23, 100)),
        ProductType.other to mutableListOf(Product(2, "rings", ProductType.other, 23, 101)),
        ProductType.food to mutableListOf(Product(3, "Apple", ProductType.food, 23, 1001)),
        ProductType.gadget to mutableListOf(Product(4, "phone", ProductType.gadget, 23, 200)),

        )

    fun findByType(type: ProductType): List<Product> {
        return memoryMap[type] ?: throw Exception("internal server error")
    }

    fun findAll(): List<Product> {
        return memoryMap.values.random()
    }

    fun save(productRequest: ProductRequest): Product {
        val index = memoryMap.values.flatten().count()
        val newProduct =
            Product(index + 1, productRequest.name, productRequest.type, productRequest.inventory, productRequest.cost)
        return saveProduct(productRequest, newProduct)
    }

    private fun saveProduct(
        productRequest: ProductRequest,
        newProduct: Product
    ): Product {
        if (memoryMap.containsKey(productRequest.type)) {
            val values = memoryMap[productRequest.type]
            values?.add(newProduct)
        } else {
            memoryMap[productRequest.type] = mutableListOf(newProduct)
        }
        return newProduct
    }

}