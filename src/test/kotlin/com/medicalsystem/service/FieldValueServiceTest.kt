package com.medicalsystem.service

import com.medicalsystem.domain.template.ValueOption
import com.medicalsystem.domain.value.MultipleSelectFieldValue
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class FieldValueServiceTest {

    @Autowired
    lateinit var fieldValueService: FieldValueService

    @Test
    fun testAutowire() = assertNotNull(fieldValueService)

    @Test
    fun testCRUD() {
        val unsavedFieldValue = MultipleSelectFieldValue(
                value = mutableSetOf(ValueOption("abc", "def"), ValueOption("ghi", "cxx")))
        val savedFieldValue = fieldValueService.save(unsavedFieldValue) as MultipleSelectFieldValue
        assertEquals(2, savedFieldValue.value.size)
    }
}