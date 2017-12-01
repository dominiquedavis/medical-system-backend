package com.medicalsystem.converter.configtomodel

import com.medicalsystem.converter.BasicConverter
import com.medicalsystem.domain.template.Form
import com.medicalsystem.init.InitialConfig
import com.medicalsystem.init.InitialConfig.ConfigForm
import org.junit.Assert
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ConfigToModelConverterTest {

    @Autowired
    lateinit var formConverter: BasicConverter<ConfigForm, Form>
    @Autowired
    lateinit var initialConfig: InitialConfig

    @Test
    fun testAutowire() {
        assertNotNull(formConverter)
        assertNotNull(initialConfig)
    }

    @Test
    fun testConvertion() {
        val configForms: Collection<ConfigForm> = initialConfig.forms
        val convertedForms: Collection<Form> = formConverter.convertAll(configForms)

        assertNotNull(convertedForms)
        assertFalse(convertedForms.isEmpty())

        convertedForms.forEach {
            assertNotNull(it)
            assertNotNull(it.name)
            assertNotNull(it.sections)
            Assert.assertFalse(it.sections.isEmpty())
            it.sections.forEach {
                assertNotNull(it)
                assertNotNull(it.fields)
                Assert.assertFalse(it.fields.isEmpty())
                it.fields.forEach {
                    assertNotNull(it.name)
                    assertNotNull(it.type)
                    assertNotNull(it.possibleValues)
                    it.possibleValues.forEach {
                        assertNotNull(it)
                        assertNotNull(it.key)
                        assertNotNull(it.value)
                    }
                }
            }
        }
    }
}