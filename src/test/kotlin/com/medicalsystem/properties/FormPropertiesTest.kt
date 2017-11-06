package com.medicalsystem.properties

import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

import com.medicalsystem.properties.FormProperties.*
import com.medicalsystem.properties.FormProperties.PropForm.*
import com.medicalsystem.properties.FormProperties.PropForm.PropSection.*

@RunWith(SpringRunner::class)
@SpringBootTest
class FormPropertiesTest {

    @Autowired
    lateinit var formProperties: FormProperties

    @Test
    fun testAutowire() {
        Assert.assertNotNull(formProperties)
    }

    @Test
    fun testBindings() {
        val forms = formProperties.forms

        Assert.assertNotNull(forms)
        Assert.assertFalse(forms.isEmpty())

        forms.forEach(this::testFormBindings)
    }

    private fun testFormBindings(form: PropForm) {
        Assert.assertNotNull(form)

        val sections = form.sections

        Assert.assertNotNull(sections)
        Assert.assertFalse(sections.isEmpty())

        sections.forEach(this::testSectionBindings)
    }

    private fun testSectionBindings(section: PropSection) {
        Assert.assertNotNull(section)

        val fields = section.fields

        Assert.assertNotNull(fields)
        Assert.assertFalse(fields.isEmpty())

        fields.forEach(this::testFieldBindings)
    }

    private fun testFieldBindings(field: PropField) {
        Assert.assertNotNull(field)

        val options = field.options

        Assert.assertNotNull(options)
    }
}