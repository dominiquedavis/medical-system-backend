package com.medicalsystem.model.value

import org.apache.commons.lang3.math.NumberUtils
import javax.persistence.*

@Entity
@Table(name = "NUMBER_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class NumberFieldValue : FieldValue<Double>() {

    @Column(name = "VALUE")
    override var value: Double = 0.0

    override fun setValueFromString(s: String) {
        this.value =
                if (NumberUtils.isCreatable(s)) {
                    s.toDouble()
                } else {
                    -1.0
                }
    }
}