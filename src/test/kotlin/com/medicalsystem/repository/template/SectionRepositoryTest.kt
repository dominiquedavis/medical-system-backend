package com.medicalsystem.repository.template

import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.Section
import com.medicalsystem.repository.template.FieldRepository
import com.medicalsystem.repository.template.SectionRepository
import org.junit.*
import org.junit.Assert.*
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.orm.jpa.JpaObjectRetrievalFailureException
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SectionRepositoryTest {

    @Autowired lateinit var sectionRepository: SectionRepository
    @Autowired lateinit var fieldRepository: FieldRepository

    @Before fun setup() = clearDatabase()
    @After fun tearDown() = clearDatabase()

    @Test fun testAutowire() {
        assertNotNull(sectionRepository)
        assertNotNull(fieldRepository)
    }

    @Test
    fun testCRUD() {
        testSaving()
        testReading()
        testUpdating()
    }

    private fun testSaving() {
        testNormalSaving()
        testCascadeSaving()
    }

    private fun testNormalSaving() {
        val unsavedSection = Section(name = "Some section")
        val savedSection = sectionRepository.save(unsavedSection)
        assertNotNull(savedSection)
        assertNotEquals(-1, savedSection.id)
    }

    private fun testCascadeSaving() {
        val unsavedSection = Section(name = "Some different section")
        val unsavedField = Field(name = "Some field", colIndex = 13)
        unsavedSection.addField(unsavedField)
        val savedSection = sectionRepository.save(unsavedSection)
        assertNotNull(savedSection)
        assertNotEquals(-1, savedSection.id)
        assertEquals(1, savedSection.fields.size)
    }

    private fun testReading() {
        val unsavedSection = Section(name = "Some ever more different section")
        val unsavedField = Field(name = "Some different field", colIndex = 15)
        unsavedSection.addField(unsavedField)
        val savedSection = sectionRepository.save(unsavedSection)
        val readSection = sectionRepository.getOne(savedSection.id)
        assertNotNull(readSection)
        assertEquals(savedSection.id, readSection.id)
        assertFalse(sectionRepository.findAll().isEmpty())
    }

    private fun testUpdating() {
        val unsavedSection = Section(name = "Some ever more different section than the previous one")
        val unsavedField = Field(name = "Some even more different field", colIndex = 17)
        unsavedSection.addField(unsavedField)
        val savedSection = sectionRepository.save(unsavedSection)

        val newSectionName = "New section name"
        val newField = Field(name = "New field", colIndex = 19)
        savedSection.name = newSectionName
        savedSection.addField(newField)

        val updatedSection = sectionRepository.save(savedSection)
        assertEquals(2, updatedSection.fields.size)
        assertEquals(newSectionName, updatedSection.name)
    }

    @Test(expected = JpaObjectRetrievalFailureException::class)
    fun testDeleting() {
        val unsavedSection = Section(name = "Some ever more different section than the previous one 1111")
        val unsavedField = Field(name = "Some even more different field 111", colIndex = 17111)
        unsavedSection.addField(unsavedField)
        val savedSection = sectionRepository.save(unsavedSection)

        sectionRepository.delete(savedSection)

        fieldRepository.getOne(savedSection.fields.iterator().next().id)
    }

    private fun clearDatabase() = sectionRepository.deleteAll()
}