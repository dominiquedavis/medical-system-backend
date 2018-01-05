package com.medicalsystem.domain.dto

import com.medicalsystem.domain.template.FieldType

abstract class AbstractFieldDTO(
        var id: Long = 0,
        var name: String = "",
        var type: FieldType = FieldType.TEXT,
        var values: List<Any> = emptyList()
)