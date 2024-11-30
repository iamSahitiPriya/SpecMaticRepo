package com.store.models

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonNode

class StringWithQuotesDeserializer : JsonDeserializer<String>() {
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): String {
        val node: JsonNode = p.codec.readTree(p)

        if (!node.isTextual) {
            throw IllegalArgumentException("Expected a string, but got ${node.nodeType}")
        }
        return node.asText()
    }
}