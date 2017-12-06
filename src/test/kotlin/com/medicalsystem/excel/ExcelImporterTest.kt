package com.medicalsystem.excel

import com.medicalsystem.excel.importer.ExcelImporter
import com.medicalsystem.excel.result.ImportResult
import com.medicalsystem.init.TemplateInitializer
import com.medicalsystem.service.FieldValueService
import com.medicalsystem.service.FormService
import com.medicalsystem.service.PatientService
import org.junit.After
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class ExcelImporterTest {

    @Autowired
    lateinit var excelImporter: ExcelImporter
    @Autowired
    lateinit var init: TemplateInitializer
    @Autowired
    lateinit var formService: FormService
    @Autowired
    lateinit var fieldValueService: FieldValueService
    @Autowired
    lateinit var patientService: PatientService

    @Before
    fun setup() {
        init.createTemplatesFromConfig()
    }
    @After
    fun tearDown() = clearDatabase()

    private fun clearDatabase() {
        fieldValueService.deleteAll()
        patientService.deleteAll()
        formService.deleteAll()
    }

    @Test
    fun testAutowire() {
        assertNotNull(excelImporter)
    }

    @Test
    fun testImport() {
        val importResult: ImportResult = excelImporter.importToDatabase("data/baza2_test.xlsx")
        println(fieldValueService.count())
        println(patientService.count())
        println(importResult.errors.size)
        importResult.errors.forEach {
            println(it)
        }
    }
}