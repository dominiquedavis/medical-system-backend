package com.medicalsystem

import com.medicalsystem.service.FieldValueService
import com.medicalsystem.service.FormService
import com.medicalsystem.service.PatientService
import org.junit.After
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@Transactional
abstract class TransactionalDatabaseClearableTest : BasicTest() {

    @Autowired
    private lateinit var formService: FormService

    @Autowired
    private lateinit var fieldValueService: FieldValueService

    @Autowired
    private lateinit var patientService: PatientService

    abstract fun clearDatabase()

    @Before
    fun setup() {
        preClear()
        clearDatabase()
    }

    @After
    fun tearDown() {
        preClear()
        clearDatabase()
    }

    private fun preClear() {
        patientService.deleteAll()
        fieldValueService.deleteAll()
        formService.deleteAll()
    }
}