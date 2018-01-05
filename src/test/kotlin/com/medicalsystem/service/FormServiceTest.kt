package com.medicalsystem.service

import com.medicalsystem.TransactionalDatabaseClearableTest
import com.medicalsystem.domain.template.Form
import com.medicalsystem.domain.template.Section
import com.medicalsystem.testSavingEntity
import org.junit.Assert.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class FormServiceTest : TransactionalDatabaseClearableTest() {

    @Autowired
    lateinit var formService: FormService

    override fun clearDatabase() {
        formService.deleteAll()
    }

    @Test
    fun testCRUD() {
        val unsavedForm = Form(name = "QAZWSX", sheetName = "EDCRFV")
        val savedForm = formService.save(unsavedForm)
        testSavingEntity(savedForm)

        val foundByID: Form? = formService.findByID(savedForm.id)
        assertNotNull(foundByID)

        val someNonExistingID = 31203910293L
        assertFalse(formService.exists(someNonExistingID))
        assertNull(formService.findByID(someNonExistingID))

        val unsavedForms = listOf(Form(name = "1", sheetName = "2"),
                Form(name = "3", sheetName = "4"),
                Form(name = "5", sheetName = "6"))

        val savedForms = formService.saveAll(unsavedForms).toList()

        assertEquals(unsavedForms.size, savedForms.size)
    }

    @Test
    fun testNested() {
        val unsavedForm = Form(name = "7", sheetName = "8")
        val unsavedSection = Section(name = "1")

        unsavedForm.addSection(unsavedSection)

        val savedForm = formService.save(unsavedForm)

        assertFalse(savedForm.sections.isEmpty())
        assertNotEquals(0, savedForm.sections.first().id)
    }
}