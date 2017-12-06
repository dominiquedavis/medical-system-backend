package com.medicalsystem.domain.value

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.id.LongIdComparableEntity
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.FieldType
import com.medicalsystem.domain.template.FieldType.*
import com.medicalsystem.domain.template.ValueOption
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Inheritance
import javax.persistence.ManyToOne

@Entity
@Inheritance
abstract class FieldValue<T>(
        @ManyToOne(fetch = FetchType.LAZY)
        var patient: Patient? = null,
        @ManyToOne(fetch = FetchType.LAZY)
        var field: Field? = null
) : LongIdComparableEntity() {
    abstract var value: T

    abstract fun setValueFromString(stringValue: String)

    fun getPossibleValues(): Set<ValueOption> =
            field?.possibleValues ?: throw RuntimeException("Field not set for value")

    companion object {
        fun getInstance(type: FieldType?): FieldValue<*> =
                when (type) {
                    TEXT -> TextFieldValue()
                    NUMBER -> NumberFieldValue()
                    DATE -> DateFieldValue()
                    SELECT -> SelectFieldValue()
                    MULTIPLE_SELECT -> MultipleSelectFieldValue()
                    null ->  throw RuntimeException("Field type not specified")
                }
    }
}