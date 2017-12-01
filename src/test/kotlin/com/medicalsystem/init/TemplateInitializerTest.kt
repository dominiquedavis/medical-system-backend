package com.medicalsystem.init

import com.medicalsystem.domain.id.IdComparableEntity
import com.medicalsystem.domain.template.Form
import com.medicalsystem.service.FormService
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class TemplateInitializerTest {

    @Autowired
    lateinit var initializer: TemplateInitializer
    @Autowired
    lateinit var formService: FormService

    @Test
    fun testAutowire() = assertNotNull(initializer)

    @Test
    fun testCreateTemplatesFromConfig() {
        val formsBefore: Collection<Form> = formService.getAll()
        val formsCreated: Collection<Form> = initializer.createTemplatesFromConfig()
        val formsAfter: Collection<Form> = formService.getAll()

        if (formsBefore.isEmpty()) {
            assertEquals(2, formsCreated.size)
            assertEquals(2, formsAfter.size)
        } else {
            //val common: Collection<Form> = getCommonElements(formsBefore, formsAfter)
        }

        formsCreated.forEach { formService.delete(it.id) }
    }

    private fun <T : IdComparableEntity<*>> getCommonElements(a: Collection<T>, b: Collection<T>): Collection<T> =
            a.filter { b.contains(it) }
}