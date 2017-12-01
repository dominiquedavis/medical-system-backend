package com.medicalsystem.converter.configtomodel

import com.medicalsystem.converter.BasicConverter
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.ValueOption
import com.medicalsystem.init.InitialConfig.ConfigForm.ConfigSection.ConfigField
import com.medicalsystem.init.InitialConfig.ConfigForm.ConfigSection.ConfigField.ConfigOption
import org.springframework.stereotype.Component

@Component
class FieldConfigToModelConverter(
        private val valueOptionConverter: BasicConverter<ConfigOption, ValueOption>
) : BasicConverter<ConfigField, Field> {

    override fun convert(from: ConfigField): Field {
        val field = Field(name = from.name, type = from.type, colIndex = from.excelColumnIndex)
        val possibleValues = valueOptionConverter.convertAll(from.options)
        possibleValues.forEach { field.addPossibleValue(it) }
        return field
    }
}