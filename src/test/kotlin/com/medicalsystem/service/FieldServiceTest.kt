package com.medicalsystem.service

import com.medicalsystem.TransactionalDatabaseClearableTest
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.FieldType
import com.medicalsystem.domain.template.Option
import com.medicalsystem.testSavingEntity
import org.junit.Assert.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class FieldServiceTest : TransactionalDatabaseClearableTest() {

    @Autowired
    lateinit var fieldService: FieldService

    override fun clearDatabase() {
        fieldService.deleteAll()
    }

    @Test
    fun testCRUD() {
        val unsavedField = Field(name = "Field name", type = FieldType.SELECT, columnIdx = 14)
        val unsavedPossibleValues = listOf(
                Option(key = "1", value = "Palący"),
                Option(key = "2", value = "Niepalący"),
                Option(key = "3", value = "Palący w przeszłości"),
                Option(key = "4", value = "Brak danych")
        )

        unsavedPossibleValues.forEach { unsavedField.addPossibleValue(it) }

        val savedField = fieldService.save(unsavedField)
        testSavingEntity(savedField)

        val fetchedField = fieldService.findByID(savedField.id)
        assertEquals(unsavedPossibleValues.size, fetchedField?.possibleValues?.size)
    }
}