package com.medicalsystem.model.dto

import com.medicalsystem.model.FieldType

data class AddingFieldDTO(
        val id: Long = 0,
        val name: String = "",
        val type: FieldType? = null,
        val possibleValues: List<FieldValuePair>? = null
)

data class FieldValuePair(
        val value: String,
        val excelKey: String
)