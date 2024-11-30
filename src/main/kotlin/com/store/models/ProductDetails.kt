package com.store.models

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.jetbrains.annotations.NotNull

data class Product(
    val id: Int,
    val name: String,
    val type: ProductType,
    val inventory: Int
)

data class ProductResponse(
    val id: Int,
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class ProductRequest(
    @NotNull
    @JsonProperty("name")
    @JsonDeserialize(using = StringWithQuotesDeserializer::class)
    val name: String,
    @NotNull
    @JsonProperty("type") val type: ProductType,
    @NotNull
    @JsonDeserialize(using = IntegerWithRangeDeserializer::class)
    @JsonProperty("inventory")
    val inventory: Int
)



