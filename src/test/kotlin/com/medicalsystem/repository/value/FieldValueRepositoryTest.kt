package com.medicalsystem.repository.value

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.ValueOption
import com.medicalsystem.domain.value.*
import com.medicalsystem.repository.PatientRepository
import com.medicalsystem.repository.template.FieldRepository
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
class FieldValueRepositoryTest {

    @Autowired lateinit var fieldValueRepository: FieldValueRepository
    @Autowired lateinit var patientRepository: PatientRepository
    @Autowired lateinit var fieldRepository: FieldRepository

    @Before fun setup() = clearDatabase()
    @After fun tearDown() = clearDatabase()

    @Test fun testAutowire() = assertNotNull(fieldValueRepository)

    @Test
    @Transactional
    fun testCRUD() {
        testSaving()
        testReading()
        testUpdating()
        testPatientAndFieldReferences()
    }

    private fun testSaving() {
        val unsavedTextFieldValue: FieldValue<*> = TextFieldValue(value = "some text value")
        val savedTextFieldValue: FieldValue<*> = fieldValueRepository.save(unsavedTextFieldValue)
        assertNotNull(savedTextFieldValue)

        val unsavedNumberFieldValue: FieldValue<*> = NumberFieldValue(value = 69.69)
        val savedNumberFieldValue: FieldValue<*> = fieldValueRepository.save(unsavedNumberFieldValue)
        assertNotNull(savedNumberFieldValue)

        val unsavedSelectFieldValue = SelectFieldValue(value = ValueOption(key = "123", value = "456"))
        val savedSelectFieldValue = fieldValueRepository.save(unsavedSelectFieldValue)
        assertNotNull(savedSelectFieldValue)

        val valueOptions = mutableSetOf(ValueOption(key = "abc", value = "def"), ValueOption(key = "ghi", value = "jkl"))
        val unsavedMultipleSelectFieldValue = MultipleSelectFieldValue(value = valueOptions)
        val savedMultipleSelectFieldValue = fieldValueRepository.save(unsavedMultipleSelectFieldValue)
        assertNotNull(savedMultipleSelectFieldValue)
    }

    private fun testReading() {
        val unsavedTextFieldValue: FieldValue<*> = TextFieldValue(value = "some text value")
        val savedTextFieldValue: FieldValue<*> = fieldValueRepository.save(unsavedTextFieldValue)
        fieldValueRepository.getOne(savedTextFieldValue.id).value
        assertNotNull(savedTextFieldValue)

        val unsavedNumberFieldValue: FieldValue<*> = NumberFieldValue(value = 69.69)
        val savedNumberFieldValue: FieldValue<*> = fieldValueRepository.save(unsavedNumberFieldValue)
        assertNotNull(savedNumberFieldValue)

        val unsavedSelectFieldValue = SelectFieldValue(value = ValueOption(key = "123", value = "456"))
        val savedSelectFieldValue = fieldValueRepository.save(unsavedSelectFieldValue)
        assertNotNull(savedSelectFieldValue)

        val valueOptions = mutableSetOf(ValueOption(key = "abc", value = "def"), ValueOption(key = "ghi", value = "jkl"))
        val unsavedMultipleSelectFieldValue = MultipleSelectFieldValue(value = valueOptions)
        val savedMultipleSelectFieldValue: MultipleSelectFieldValue = fieldValueRepository.save(unsavedMultipleSelectFieldValue)
        assertNotNull(savedMultipleSelectFieldValue)

        val read: FieldValue<*> = fieldValueRepository.getOne(savedMultipleSelectFieldValue.id)
        assertTrue(read is MultipleSelectFieldValue)
        val size = (read as MultipleSelectFieldValue).value.size
        read.value.forEach { println(it.toString() + " " + it.hashCode()) }
        assertEquals(2, size)

        assertFalse(fieldValueRepository.findAll().isEmpty())
        assertNotNull(fieldValueRepository.getOne(savedTextFieldValue.id))
        assertNotNull(fieldValueRepository.getOne(savedNumberFieldValue.id))
        assertNotNull(fieldValueRepository.getOne(savedSelectFieldValue.id))
    }

    private fun testUpdating() {
        val unsavedTextFieldValue = TextFieldValue(value = "some text value")
        val savedTextFieldValue = fieldValueRepository.save(unsavedTextFieldValue)
        assertNotNull(savedTextFieldValue)

        val unsavedNumberFieldValue: FieldValue<*> = NumberFieldValue(value = 69.69)
        val savedNumberFieldValue: FieldValue<*> = fieldValueRepository.save(unsavedNumberFieldValue)
        assertNotNull(savedNumberFieldValue)

        savedTextFieldValue.value = "fff"
        assertEquals("fff", fieldValueRepository.save(savedTextFieldValue).value)
    }

    private fun testPatientAndFieldReferences() {
        val unsavedPatient = Patient()
        val unsavedField = Field(name = "fieldff", colIndex = 123123)
        val unsavedValue = TextFieldValue(value = "some text value")
        val savedPatient = patientRepository.save(unsavedPatient)
        val savedField = fieldRepository.save(unsavedField)
        unsavedValue.patient = savedPatient
        unsavedValue.field = savedField
        val savedValue = fieldValueRepository.save(unsavedValue)
        assertNotNull(savedPatient)
        assertNotNull(savedField)
        assertNotNull(savedValue)

        fieldValueRepository.delete(savedValue.id)

        assertFalse(fieldValueRepository.exists(savedValue.id))
        assertTrue(patientRepository.exists(savedPatient.id))
        assertTrue(fieldRepository.exists(savedField.id))
    }

    private fun clearDatabase() {
        fieldValueRepository.deleteAll()
        patientRepository.deleteAll()
        fieldRepository.deleteAll()
    }
}