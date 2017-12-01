package com.medicalsystem.domain

import com.medicalsystem.domain.template.Form
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ModelTest {

    @Test
    fun testIdComparableEntity() {
        testForms()
    }

    private fun testForms() {
        val form1 = Form()
        val form2 = Form()

        form1.id = 13
        form2.id = 14

        Assert.assertTrue(form1 == form1)
        Assert.assertTrue(form2 == form2)
        Assert.assertFalse(form1 == form2)
    }
}