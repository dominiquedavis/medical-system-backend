package com.medicalsystem.domain.value

import com.medicalsystem.util.DateUtils
import java.time.LocalDate
import javax.persistence.Column
import javax.persistence.Entity

@Entity
class DateFieldValue(@Column(name = "DATE_VALUE") override var value: LocalDate? = null) : FieldValue<LocalDate?>() {

    override fun setValueFromString(stringValue: String) {
        if (stringValue != "") {
            value = DateUtils.fromString(stringValue)
        }
    }
}