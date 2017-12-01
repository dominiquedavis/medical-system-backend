package com.medicalsystem.converter.configtomodel

import com.medicalsystem.converter.BasicConverter
import com.medicalsystem.domain.template.Form
import com.medicalsystem.domain.template.Section
import com.medicalsystem.init.InitialConfig.*
import com.medicalsystem.init.InitialConfig.ConfigForm.*
import org.springframework.stereotype.Component

@Component
class FormConfigToModelConverter(
        private val sectionConverter: BasicConverter<ConfigSection, Section>
) : BasicConverter<ConfigForm, Form> {

    override fun convert(from: ConfigForm): Form {
        val form = Form(name = from.name, sheetIndex = from.index)
        val sections = sectionConverter.convertAll(from.sections)
        sections.forEach { form.addSection(it) }
        return form
    }
}