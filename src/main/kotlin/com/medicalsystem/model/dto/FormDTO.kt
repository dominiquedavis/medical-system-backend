package com.medicalsystem.model.dto

data class FormDTO(
        val id: Long = 0,
        val type: String = "",
        val sections: List<SectionDTO> = emptyList()
)