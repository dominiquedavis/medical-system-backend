package com.medicalsystem.repository.template

import com.medicalsystem.domain.template.Form
import com.medicalsystem.domain.template.Section
import com.medicalsystem.repository.template.FormRepository
import org.junit.After
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class FormRepositoryTest {

    @Autowired lateinit var formRepository: FormRepository

    @Before
    fun setup() = clearDatabase()

    @After
    fun tearDown() = clearDatabase()

    @Test
    fun testAutowire() = Assert.assertNotNull(formRepository)

    @Test
    fun testCRUD() {
        testSaving()
        testReading()
        testUpdating()
        testDeleting()
    }

    private fun testSaving() {
        val unsavedForm = Form(name = "Some form", sheetIndex = 44)
        val unsavedSection = Section(name = "Some different section")
        unsavedForm.addSection(unsavedSection)
        val savedForm = formRepository.save(unsavedForm)

        assertNotNull(savedForm)
        assertEquals(1, savedForm.sections.size)
    }

    private fun testReading() {
        val unsavedForm = Form(name = "Some other form", sheetIndex = 45)
        val unsavedSection = Section(name = "Some other different section")
        unsavedForm.addSection(unsavedSection)
        val savedForm = formRepository.save(unsavedForm)

        assertFalse(formRepository.findAll().isEmpty())
        assertNotNull(formRepository.getOne(savedForm.id))

        val notExistsFailing =
            try {
                formRepository.getOne(-999L)
                false
            } catch (e: JpaObjectRetrievalFailureException) {
                true
            }
        assertTrue(notExistsFailing)
    }

    private fun testUpdating() {

    }

    private fun testDeleting() {
        formRepository.deleteAll()
        assertTrue(formRepository.findAll().isEmpty())
    }

    private fun clearDatabase() = formRepository.deleteAll()
}