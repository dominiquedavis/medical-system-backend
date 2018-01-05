package com.medicalsystem.init

import com.medicalsystem.converter.configtotemplate.FormConfigToTemplateConverter
import com.medicalsystem.domain.template.Form
import com.medicalsystem.init.TemplateConfiguration.ConfigForm
import com.medicalsystem.service.FormService
import com.medicalsystem.util.logger
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TemplateInitializer(
        private val templateConfiguration: TemplateConfiguration,
        private val converter: FormConfigToTemplateConverter,
        private val formService: FormService
) {

    @Transactional
    fun loadTemplateFromConfig() {
        val configForms: List<ConfigForm> = templateConfiguration.forms
        val converterForms: List<Form> = converter.convertAll(configForms)

        converterForms.forEach {
            formService.save(it)
            logger().info("Form created: $it")
        }
    }
}