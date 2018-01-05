package com.medicalsystem.service

import com.medicalsystem.TransactionalDatabaseClearableTest
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.FieldType
import com.medicalsystem.domain.template.Option
import com.medicalsystem.domain.value.*
import com.medicalsystem.testSavingEntity
import org.junit.Assert.*
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class FieldValueServiceTest : TransactionalDatabaseClearableTest() {

    @Autowired
    lateinit var fieldValueService: FieldValueService

    @Autowired
    lateinit var fieldService: FieldService

    override fun clearDatabase() {
        fieldValueService.deleteAll()
    }

    @Test
    fun testCRUD() {
        val unsavedTextFieldValue = TextFieldValue(value = "some text value")
        val savedTextFieldValue: TextFieldValue = fieldValueService.save(unsavedTextFieldValue)
        testSavingEntity(savedTextFieldValue)

        val fetchedTextFieldValue: FieldValue<*>? = fieldValueService.findByID(savedTextFieldValue.id)
        assertNotNull(fetchedTextFieldValue)
        assertTrue(fetchedTextFieldValue is TextFieldValue)

        val unsavedNumberFieldValue: FieldValue<*> = NumberFieldValue(value = 21.37)
        val savedNumberFieldValue: FieldValue<*> = fieldValueService.save(unsavedNumberFieldValue)
        testSavingEntity(savedNumberFieldValue)

        val unsavedDateFieldValue = DateFieldValue(value = LocalDate.now())
        val savedDateFieldValue = fieldValueService.save(unsavedDateFieldValue)
        testSavingEntity(savedDateFieldValue)

        val field = fieldService.save(Field(type = FieldType.SELECT,
                possibleValues = setOf(Option("1", "2"), Option("3", "4"), Option("5", "6")).toMutableSet()))

        val unsavedSelectFieldValue = SelectFieldValue(value = field.possibleValues.first())
        val savedFieldValue = fieldValueService.save(unsavedSelectFieldValue)
        testSavingEntity(savedFieldValue)

        val unsavedMultipleSelectFieldValue = MultipleSelectFieldValue(value =
                setOf(field.possibleValues.first(), field.possibleValues.last()).toMutableSet())
        val savedMultipleSelectFieldValue = fieldValueService.save(unsavedMultipleSelectFieldValue)
        testSavingEntity(savedMultipleSelectFieldValue)
        assertEquals(2, savedMultipleSelectFieldValue.value.size)

        val allFieldValues: Collection<FieldValue<*>> = fieldValueService.findAll()
        assertFalse(allFieldValues.isEmpty())

        allFieldValues.forEach {
            it.field
            println(it.value)
        }
    }

    @Test
    fun testMultipleSelectFieldValues() {
        val unsavedOptions = mutableSetOf(Option("1", "2"), Option("3", "4"), Option("5", "6"))
        val unsavedField = Field(name = "some field", type = FieldType.MULTIPLE_SELECT, possibleValues = unsavedOptions)

        val savedField = fieldService.save(unsavedField)
        val savedOptions = savedField.possibleValues.toList()

        val unsavedFieldValue1 = MultipleSelectFieldValue(value = mutableSetOf(savedOptions[0], savedOptions[1]))
        val savedFieldValue1 = fieldValueService.save(unsavedFieldValue1)

        val unsavedFieldValue2 = MultipleSelectFieldValue(value = mutableSetOf(savedOptions[0], savedOptions[1]))
        val savedFieldValue2 = fieldValueService.save(unsavedFieldValue2)

        savedField.possibleValues.forEach {
            println(it)
        }
    }
}