package com.medicalsystem.init

import com.medicalsystem.TransactionalDatabaseClearableTest
import com.medicalsystem.service.FormService
import org.junit.Assert.assertEquals
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class TemplateInitializerTest : TransactionalDatabaseClearableTest() {

    @Autowired
    lateinit var templateInitializer: TemplateInitializer

    @Autowired
    lateinit var formService: FormService

    override fun clearDatabase() {
        formService.deleteAll()
    }

    @Test
    fun test() {
        templateInitializer.loadTemplateFromConfig()

        assertEquals(2, formService.count())
    }
}