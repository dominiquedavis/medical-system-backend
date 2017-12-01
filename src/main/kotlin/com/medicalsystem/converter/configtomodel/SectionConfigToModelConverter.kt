package com.medicalsystem.converter.configtomodel

import com.medicalsystem.converter.BasicConverter
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.Section
import com.medicalsystem.init.InitialConfig.ConfigForm.ConfigSection
import com.medicalsystem.init.InitialConfig.ConfigForm.ConfigSection.ConfigField
import org.springframework.stereotype.Component

@Component
class SectionConfigToModelConverter(
        private val fieldConverter: BasicConverter<ConfigField, Field>
) : BasicConverter<ConfigSection, Section> {

    override fun convert(from: ConfigSection): Section {
        val section = Section(name = from.name)
        val fields = fieldConverter.convertAll(from.fields)
        fields.forEach { section.addField(it) }
        return section
    }
}