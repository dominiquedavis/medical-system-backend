package com.medicalsystem.domain.dto

data class FormDTO(
        var id: Long = 0,
        var name: String = "",
        var sections: List<SectionDTO> = emptyList()
)