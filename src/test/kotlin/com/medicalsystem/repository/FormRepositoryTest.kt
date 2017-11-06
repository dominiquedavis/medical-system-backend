package com.medicalsystem.repository

import com.medicalsystem.model.Form
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
class FormRepositoryTest {

    @Autowired lateinit var formRepository: FormRepository
    @Autowired lateinit var sectionRepository: SectionRepository

    @Before @After
    fun tearDown() = formRepository.deleteAll()

    @Test
    fun testAutowire() {
        Assert.assertNotNull(formRepository)
    }

    @Test
    fun testCascadeSaving() {
        val form = Form(name = "Form 1")
        val section = Section(name = "Section 1")

        form.sections.add(section)
        section.form = form

        val savedForm = formRepository.save(form)
        val savedSection = savedForm.sections[0]

        Assert.assertEquals(savedSection.id, sectionRepository.findOne(savedSection.id).id)
    }

    @Test
    fun testOrphanRemoval() {
        val form = Form(name = "Form 1")
        val section = Section(name = "Section 1")

        form.sections.add(section)
        section.form = form

        val savedForm = formRepository.save(form)
        val savedSection = savedForm.sections[0]

        formRepository.deleteAll()

        Assert.assertNull(sectionRepository.findOne(savedSection.id))
    }

    @Test
    fun testFindBySheetIndex() {
        val sheetIndex = 13
        val form = Form(sheetIndex = sheetIndex)
        formRepository.save(form)
        Assert.assertNotNull(formRepository.findBySheetIndex(sheetIndex))
    }
}