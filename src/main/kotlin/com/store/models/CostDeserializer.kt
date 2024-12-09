package com.store.models

class CostDeserializer : BaseRangeDeserializer() {

    override fun validate(value: Int) {
        if (value < 0) {
            throw IllegalArgumentException("The value must be greater than or equal to 0.")
        }
    }
}