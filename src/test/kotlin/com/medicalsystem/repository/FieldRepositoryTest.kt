package com.medicalsystem.repository

import com.medicalsystem.model.Field
import com.medicalsystem.model.FieldType
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
class FieldRepositoryTest {

    @Autowired lateinit var fieldRepository: FieldRepository
    @Autowired lateinit var formRepository: FormRepository

    @Before @After
    fun tearDown() {
        formRepository.deleteAll()
        fieldRepository.deleteAll()
    }

    @Test
    fun testAutowire() {
        Assert.assertNotNull(fieldRepository)
    }

    @Test
    fun testSave() {
        val field = Field(name = "Pole 1", type = FieldType.SELECT)
        val saved = fieldRepository.save(field)
        Assert.assertTrue(saved.id > 0)
    }

    @Test
    fun testFindAllByForm() {
        val form = Form()
        val sections: List<Section> = listOf(Section(), Section(), Section())
        val fields: List<List<Field>> = listOf(
                listOf(Field(), Field(), Field()),
                listOf(Field(), Field(), Field()),
                listOf(Field(), Field(), Field())
        )

        form.sections = sections.toMutableList()
        sections.forEach { it.form = form }

        sections.forEachIndexed { index, section -> section.fields = fields[index].toMutableList() }
        fields.forEachIndexed { index, list -> list.forEach { it.section = sections[index] } }

        val savedForm = formRepository.save(form)

        Assert.assertEquals(9, fieldRepository.findAllByForm(savedForm).size)
    }
}
