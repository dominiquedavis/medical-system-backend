package com.medicalsystem.init

import com.medicalsystem.factory.FormFactory
import com.medicalsystem.model.Form
import com.medicalsystem.properties.FormProperties
import com.medicalsystem.service.FormService
import com.medicalsystem.util.logger
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Initializer @Autowired constructor(
        val formProperties: FormProperties,
        val formService: FormService) {

    fun runInitialConfiguration() {
        val forms: List<Form> = FormFactory.createFromProperties(formProperties.forms)
        forms.forEach { form ->
            formService.save(form)
            logger().info("Form saved: $form")
        }
    }
}