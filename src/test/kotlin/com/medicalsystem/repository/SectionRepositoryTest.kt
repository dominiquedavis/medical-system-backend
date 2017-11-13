package com.medicalsystem.repository

import com.medicalsystem.model.Field
import com.medicalsystem.model.Section
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
class SectionRepositoryTest {

    @Autowired lateinit var sectionRepository: SectionRepository
    @Autowired lateinit var fieldRepository: FieldRepository

    @Before @After
    fun tearDown() = sectionRepository.deleteAll()

    @Test
    fun testAutowire() {
        Assert.assertNotNull(sectionRepository)
    }

    @Test
    fun testSave() {
        val section = Section(name = "Section 1")
        val saved = sectionRepository.save(section)
        Assert.assertTrue(saved.id > 0)
    }

    @Test
    fun testCascadeSaving() {
        val section = Section(name = "Section 1")
        val field = Field(name = "Field 1")

        section.fields.add(field)
        field.section = section

        val savedSection = sectionRepository.save(section)
        val savedField = savedSection.fields.iterator().next()

        Assert.assertEquals(savedField.id, fieldRepository.findOne(savedField.id).id)
    }

    @Test
    fun testOrphanRemoval() {
        val section = Section(name = "Section 2")
        val field = Field(name = "Field 2")

        section.fields.add(field)
        field.section = section

        val savedSection = sectionRepository.save(section)
        val savedField = savedSection.fields.iterator().next()

        sectionRepository.deleteAll()

        Assert.assertNull(fieldRepository.findOne(savedField.id))
    }
}