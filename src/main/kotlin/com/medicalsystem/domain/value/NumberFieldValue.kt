package com.medicalsystem.domain.value

import javax.persistence.Column
import javax.persistence.Entity

@Entity
class NumberFieldValue(@Column(name = "NUMBER_VALUE") override var value: Double? = null) : FieldValue<Double?>() {

    override fun setValueFromString(stringValue: String) {
        if (stringValue != "") {
            value = stringValue.toDouble()
        }
    }
}