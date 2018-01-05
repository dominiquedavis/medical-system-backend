package com.medicalsystem.domain.dto

data class AddingFieldDTO(var possibleValues: List<PossibleValueDTO> = emptyList()) : AbstractFieldDTO()