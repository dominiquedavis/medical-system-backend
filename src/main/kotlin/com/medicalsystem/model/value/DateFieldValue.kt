package com.medicalsystem.model.value

import com.medicalsystem.util.DateUtils
import java.text.ParseException
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "DATE_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class DateFieldValue : FieldValue<Date?>() {

    @Column(name = "VALUE")
    override var value: Date? = Date()

    override fun setValueFromString(s: String) {
        this.value =
                try {
                    DateUtils.fromString(s)
                } catch (e: ParseException) {
                    null
                }
    }
}