package com.store.models

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

abstract class BaseIntDeserializer : JsonDeserializer<Int>() {

    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Int {
        val node: JsonNode = p.codec.readTree(p)
        if (!node.isInt) {
            throw IllegalArgumentException("The value must be an integer.")
        }

        val value = node.asInt()
        validate(value)
        return value
    }

   protected abstract fun validate(value: Int)

}