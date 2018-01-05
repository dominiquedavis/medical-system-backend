package com.medicalsystem.domain.dto

import com.medicalsystem.domain.template.FieldType

data class FieldDTO(
        var id: Long = 0,
        var name: String = "",
        var type: FieldType = FieldType.TEXT,
        var values: List<Any> = emptyList(),
        var possibleValues: List<Any> = emptyList()
)