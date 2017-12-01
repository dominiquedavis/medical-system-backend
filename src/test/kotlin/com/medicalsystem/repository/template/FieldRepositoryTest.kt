package com.medicalsystem.repository.template

import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.FieldType
import com.medicalsystem.domain.template.ValueOption
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@RunWith(SpringRunner::class)
@SpringBootTest
class FieldRepositoryTest {

    @Autowired lateinit var fieldRepository: FieldRepository

    @Before fun setup() = clearDatabase()
    @After fun tearDown() = clearDatabase()

    @Test fun testAutowire() = assertNotNull(fieldRepository)

    @Test
    fun testCRUD() {
        val fieldName = "Some field"
        val fieldType = FieldType.TEXT
        val unsavedField = Field(name = fieldName, type = fieldType)
        val savedField = fieldRepository.save(unsavedField)

        // Saving
        assertNotNull(savedField)
        assertFalse(savedField.id == -1L)
        assertEquals(fieldName, savedField.name)
        assertEquals(fieldType, savedField.type)

        // Reading
        val readField = fieldRepository.getOne(savedField.id)
        assertNotNull(readField)
        assertEquals(savedField.id, readField.id)
        assertFalse(fieldRepository.findAll().isEmpty())

        // Updating
        val newFieldName = "New field name"
        val oldId = savedField.id
        savedField.name = newFieldName
        val updatedField = fieldRepository.save(savedField)
        assertNotNull(updatedField)
        assertEquals(oldId, updatedField.id)
        assertEquals(newFieldName, updatedField.name)

        // Deleting
        (1..10).forEach { fieldRepository.save(Field(colIndex = it)) }
        fieldRepository.deleteAll()
        assertEquals(0, fieldRepository.count())
    }

    @Test(expected = JpaObjectRetrievalFailureException::class)
    fun testDeleteOne() {
        val savedField = fieldRepository.save(Field())
        fieldRepository.delete(savedField.id)
        fieldRepository.getOne(savedField.id)
        fieldRepository.delete(savedField.id)
    }

    @Test
    @Transactional
    fun testFieldAndValueOptionMapping() {
        val unsavedField1 = Field(name = "Field", colIndex = 555555)
        val unsavedField2 = Field(name = "Field2", colIndex = 5555552)
        val unsavedOption1 = ValueOption(key = "abc", value = "def")
        val unsavedOption2 = ValueOption(key = "ghi", value = "jkl")
        val unsavedOption3 = ValueOption(key = "mno", value = "pqr")
        unsavedField1.addPossibleValue(unsavedOption1)
        unsavedField1.addPossibleValue(unsavedOption2)
        unsavedField2.addPossibleValue(unsavedOption2)
        unsavedField2.addPossibleValue(unsavedOption3)

        println(unsavedField1)
        println(unsavedField2)

        fieldRepository.save(unsavedField1)
        fieldRepository.save(unsavedField2)
    }

    private fun clearDatabase() = fieldRepository.deleteAll()
}