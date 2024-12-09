package com.store.models

class InventoryDeserializer : BaseIntDeserializer() {

    override fun validate(value: Int) {
        if (value <= 0 || value > 9999) {
            throw IllegalArgumentException("The value must be greater than 0 and less than 10,000.")
        }
    }
}