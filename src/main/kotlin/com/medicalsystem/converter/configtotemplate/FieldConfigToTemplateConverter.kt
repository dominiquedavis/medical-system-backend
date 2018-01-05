package com.medicalsystem.converter.configtotemplate

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.template.Field
import com.medicalsystem.init.TemplateConfiguration.ConfigForm.ConfigSection.ConfigField
import org.springframework.stereotype.Component

/**
 * Converts FieldConfig to a template Field.
 */
@Component
class FieldConfigToTemplateConverter(private val optionConverter: OptionConfigToTemplateConverter) : Converter<ConfigField, Field> {

    override fun convert(source: ConfigField): Field {
        val field = Field(name = source.name, type = source.type, columnIdx = source.excelColumnIndex)
        val possibleValues = optionConverter.convertAll(source.options)

        possibleValues.forEach {
            field.addPossibleValue(it)
        }

        return field
    }
}