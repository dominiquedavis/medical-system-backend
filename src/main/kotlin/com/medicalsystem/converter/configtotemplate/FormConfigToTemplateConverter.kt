package com.medicalsystem.converter.configtotemplate

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.template.Form
import com.medicalsystem.init.TemplateConfiguration.ConfigForm
import org.springframework.stereotype.Component

/**
 * Converts FormConfig to a template Form.
 */
@Component
class FormConfigToTemplateConverter(private val sectionConverter: SectionConfigToTemplateConverter) : Converter<ConfigForm, Form> {

    override fun convert(source: ConfigForm): Form {
        val form = Form(name = source.type, sheetName = source.name)
        val sections = sectionConverter.convertAll(source.sections)

        sections.forEach {
            form.addSection(it)
        }

        return form
    }
}