package com.medicalsystem.converter.configtomodel

import com.medicalsystem.converter.BasicConverter
import com.medicalsystem.domain.template.ValueOption
import com.medicalsystem.init.InitialConfig.ConfigForm.ConfigSection.ConfigField.ConfigOption
import org.springframework.stereotype.Component

@Component
class ValueOptionConfigToModelConverter : BasicConverter<ConfigOption, ValueOption> {
    override fun convert(from: ConfigOption): ValueOption =
            ValueOption(key = from.key, value = from.`val`)
}