package com.medicalsystem.converter.dtototemplate

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.dto.PossibleValueDTO
import com.medicalsystem.domain.template.Option
import org.springframework.stereotype.Component

@Component
class PossibleValueDTOToOptionConverter : Converter<PossibleValueDTO, Option> {

    override fun convert(source: PossibleValueDTO): Option =
            Option(key = source.excelKey, value = source.value)
}