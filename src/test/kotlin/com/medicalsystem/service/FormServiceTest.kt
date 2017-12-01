package com.medicalsystem.service

import com.medicalsystem.domain.template.Form
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class FormServiceTest {
    @Autowired lateinit var formService: FormService
    @Before fun setup() = clearDatabase()
    @After fun tearDown() = clearDatabase()
    private fun clearDatabase() = formService.deleteAll()
    @Test fun testAutowire() = assertNotNull(formService)

    @Test
    fun testCRUD() {
        val someNonExistingID = 31203910293L
        val unsavedForm = Form()
        val savedForm = formService.save(unsavedForm)
        assertNotNull(savedForm)
        assertNotNull(formService.getByID(savedForm.id))
        assertTrue(formService.exists(savedForm.id))
        assertFalse(formService.exists(someNonExistingID))
        assertNull(formService.getByID(someNonExistingID))
        formService.delete(someNonExistingID)
        formService.delete(savedForm.id)
        assertFalse(formService.exists(savedForm.id))
    }
}