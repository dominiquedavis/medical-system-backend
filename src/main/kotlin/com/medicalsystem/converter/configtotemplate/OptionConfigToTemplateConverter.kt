package com.medicalsystem.converter.configtotemplate

import com.medicalsystem.converter.Converter
import com.medicalsystem.domain.template.Option
import org.springframework.stereotype.Component
import com.medicalsystem.init.TemplateConfiguration.ConfigForm.ConfigSection.ConfigField.ConfigOption

/**
 * Converts OptionConfig to a template Option.
 */
@Component
class OptionConfigToTemplateConverter : Converter<ConfigOption, Option> {

    override fun convert(source: ConfigOption): Option {
        return Option(key = source.key, value = source.`val`)
    }
}