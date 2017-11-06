package com.medicalsystem.model.dto

import com.medicalsystem.model.FieldType

data class FieldDTO(
        val id: Long = 0,
        val name: String = "",
        val type: FieldType? = null,
        val values: List<*>? = null,
        val possibleValues: List<*>? = null
)