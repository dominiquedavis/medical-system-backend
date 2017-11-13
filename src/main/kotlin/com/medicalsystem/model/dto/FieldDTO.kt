package com.medicalsystem.model.dto

import com.medicalsystem.model.FieldType

data class FieldDTO(
        var id: Long = 0,
        var name: String = "",
        var type: FieldType,
        var values: List<*>? = null,
        var possibleValues: List<*>? = null
)