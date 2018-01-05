package com.medicalsystem.domain.dto

data class SectionDTO(
        var id: Long = 0,
        var name: String = "",
        var fields: List<FieldDTO> = emptyList()
)