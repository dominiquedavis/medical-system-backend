package com.medicalsystem.model.dto

import com.medicalsystem.model.FieldType

data class AddingFieldDTO(
        var id: Long = 0,
        var name: String = "",
        var type: FieldType? = null,
        var values: List<Any>? = null,
        var possibleValues: List<SelectFieldValue>? = null
)

data class SelectFieldValue(
        var value: String = "",
        var excelKey: String = ""
)