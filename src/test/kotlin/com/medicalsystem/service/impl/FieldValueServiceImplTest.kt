package com.medicalsystem.service.impl

import com.medicalsystem.model.value.*
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
class FieldValueServiceImplTest {

    @Autowired
    lateinit var fieldValueService: FieldValueServiceImpl

    @Before @After
    fun setup() = fieldValueService.deleteAll()

    @Test
    fun testAutowire() {
        Assert.assertNotNull(fieldValueService)
        Assert.assertNotNull(fieldValueService.saveRepository)
        Assert.assertNotNull(fieldValueService.readRepositories)
        Assert.assertEquals(5, fieldValueService.readRepositories.size)
    }

    @Test
    fun testSave() {
        val fieldValue = TextFieldValue()
        fieldValue.value = "Value"

        val savedFieldValue = fieldValueService.save(fieldValue)

        Assert.assertTrue(savedFieldValue.id > 0)
    }

    @Test
    fun testGetAll() {
        fieldValueService.save(TextFieldValue())
        fieldValueService.save(DateFieldValue())
        fieldValueService.save(NumberFieldValue())
        fieldValueService.save(SelectFieldValue())
        fieldValueService.save(MultipleSelectFieldValue())

        val all = fieldValueService.getAll()

        Assert.assertTrue(all.size >= 5)
    }

    @Test
    fun testGetById() {
        val id = fieldValueService.save(TextFieldValue()).id
        val fieldValue = fieldValueService.getById(id)

        Assert.assertNotNull(fieldValue)
        Assert.assertEquals(id, fieldValue?.id)
    }

    @Test
    fun testExists() {
        val id = fieldValueService.save(TextFieldValue()).id

        Assert.assertTrue(fieldValueService.exists(id))
        Assert.assertFalse(fieldValueService.exists(4837432874))
    }

    @Test
    fun testDelete() {
        val id = fieldValueService.save(TextFieldValue()).id

        Assert.assertTrue(fieldValueService.exists(id))

        fieldValueService.delete(id)

        Assert.assertFalse(fieldValueService.exists(id))
    }

    @Test
    fun testDeleteAll() {
        fieldValueService.save(TextFieldValue())
        fieldValueService.save(DateFieldValue())
        fieldValueService.save(NumberFieldValue())
        fieldValueService.save(SelectFieldValue())
        fieldValueService.save(MultipleSelectFieldValue())

        Assert.assertFalse(fieldValueService.getAll().isEmpty())

        fieldValueService.deleteAll()

        Assert.assertTrue(fieldValueService.getAll().isEmpty())
    }
}