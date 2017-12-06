package com.medicalsystem.domain.value

import com.medicalsystem.domain.template.ValueOption
import com.medicalsystem.util.getByKey
import javax.persistence.*

@Entity
class SelectFieldValue(
    @OneToOne(fetch = FetchType.LAZY, cascade = [(CascadeType.ALL)])
    @JoinColumn(name = "SELECT_VALUE")
    override var value: ValueOption? = null) : FieldValue<ValueOption?>() {

    override fun setValueFromString(stringValue: String) {
        if (stringValue != "") {
            value = getPossibleValues().getByKey(stringValue)
        }
    }
}