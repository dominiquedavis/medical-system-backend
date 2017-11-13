package com.medicalsystem.factory

import com.medicalsystem.model.Field
import com.medicalsystem.model.FieldType
import com.medicalsystem.model.FieldType.*
import com.medicalsystem.model.value.*

object FieldValueFactory {

    fun fromFieldAndStringValue(field: Field, value: String): FieldValue<*> {
        val fieldValue: FieldValue<*> = createInstance(field.type)
        fieldValue.field = field
        fieldValue.setValueFromString(value.trim())
        return fieldValue
    }

    fun createInstance(fieldType: FieldType): FieldValue<*> =
            when (fieldType) {
                TEXT -> TextFieldValue()
                NUMBER -> NumberFieldValue()
                DATE -> DateFieldValue()
                SELECT -> SelectFieldValue()
                MULTIPLE_SELECT -> MultipleSelectFieldValue()
            }
}