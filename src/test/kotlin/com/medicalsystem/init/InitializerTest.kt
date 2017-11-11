package com.medicalsystem.init

import com.medicalsystem.properties.FormProperties
import com.medicalsystem.service.FormService
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class InitializerTest {

    @Autowired lateinit var initializer: Initializer
    @Autowired lateinit var formService: FormService
    @Autowired lateinit var formProperties: FormProperties

    @Before @After
    fun setup() = formService.deleteAll()

    @Test
    fun testAutowire() {
        Assert.assertNotNull(initializer)
    }

    @Test
    fun testRunInitialConfiguration() {
        val formsToCreate = formProperties.forms.size
        val sectionsToCreate = formProperties.forms.flatMap { it.sections }.size
        val fieldsToCreate = formProperties.forms.flatMap { it.sections }.flatMap { it.fields }.size

        initializer.runInitialFormConfiguration()

        val forms = formService.getAll()

        val formsActuallyCreated = forms.size
        val sectionsActuallyCreated = forms.flatMap { it.sections }.size
        val fieldsActuallyCreated = forms.flatMap { it.sections }.flatMap { it.fields }.size

        Assert.assertEquals(formsToCreate, formsActuallyCreated)
        Assert.assertEquals(sectionsToCreate, sectionsActuallyCreated)
        Assert.assertEquals(fieldsToCreate, fieldsActuallyCreated)
    }
}