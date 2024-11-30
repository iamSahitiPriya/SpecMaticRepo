package com.store.models

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

class IntegerWithRangeDeserializer : JsonDeserializer<Int>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): Int {
        val node: JsonNode = p.codec.readTree(p)

        if (!node.isInt) {
            throw IllegalArgumentException("The value must be an integer.")
        }

        val value = node.asInt()

        if (value <= 0 || value >= 10000) {
            throw IllegalArgumentException("The value must be greater than 0 and less than 10,000.")
        }

        return value
    }
}