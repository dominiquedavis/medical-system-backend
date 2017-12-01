package com.medicalsystem.init

import com.medicalsystem.converter.BasicConverter
import com.medicalsystem.domain.template.Form
import com.medicalsystem.init.InitialConfig.ConfigForm
import com.medicalsystem.service.FormService
import com.medicalsystem.util.logger
import org.springframework.stereotype.Component

@Component
class TemplateInitializer(
        private val initialConfig: InitialConfig,
        private val configToModelConverter: BasicConverter<ConfigForm, Form>,
        private val formService: FormService) {

    fun createTemplatesFromConfig(): Collection<Form> {
        val configForms: Collection<ConfigForm> = initialConfig.forms
        val modelForms: Collection<Form> = configToModelConverter.convertAll(configForms)
        modelForms.forEach {
            formService.save(it)
            logger().info("Form created: $it")
        }
        return modelForms
    }
}