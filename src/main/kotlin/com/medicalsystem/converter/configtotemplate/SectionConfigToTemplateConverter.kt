package com.medicalsystem.converter.configtotemplate

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.template.Section
import com.medicalsystem.init.TemplateConfiguration.ConfigForm.ConfigSection
import org.springframework.stereotype.Component

/**
 * Converts SectionConfig to a template Section.
 */
@Component
class SectionConfigToTemplateConverter(private val fieldConverter: FieldConfigToTemplateConverter) : Converter<ConfigSection, Section> {

    override fun convert(source: ConfigSection): Section {
        val section = Section(name = source.name)
        val fields = fieldConverter.convertAll(source.fields)

        fields.forEach {
            section.addField(it)
        }

        return section
    }
}