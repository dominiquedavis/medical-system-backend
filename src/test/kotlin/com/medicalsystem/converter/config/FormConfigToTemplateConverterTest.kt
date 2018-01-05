package com.medicalsystem.converter.config

import com.medicalsystem.BasicTest
import com.medicalsystem.converter.configtotemplate.FormConfigToTemplateConverter
import com.medicalsystem.domain.template.Form
import com.medicalsystem.init.TemplateConfiguration
import com.medicalsystem.init.TemplateConfiguration.ConfigForm
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class FormConfigToTemplateConverterTest : BasicTest() {

    @Autowired
    lateinit var converter: FormConfigToTemplateConverter

    @Autowired
    lateinit var templateConfiguration: TemplateConfiguration

    @Test
    fun test() {
        val configForms: List<ConfigForm> = templateConfiguration.forms
        val convertedForms: List<Form> = converter.convertAll(configForms)

        assertEquals(configForms.size, convertedForms.size)
    }
}