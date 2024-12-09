package com.store.models

class CostDeserializer : BaseIntDeserializer() {

    override fun validate(value: Int) {
        if (value < 0) {
            throw IllegalArgumentException("The value must be greater than or equal to 0.")
        }
    }
}