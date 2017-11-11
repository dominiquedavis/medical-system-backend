package com.medicalsystem.model.dto

data class SectionDTO(
        val id: Long = 0,
        val name: String = "",
        val fields: List<FieldDTO>? = null
)