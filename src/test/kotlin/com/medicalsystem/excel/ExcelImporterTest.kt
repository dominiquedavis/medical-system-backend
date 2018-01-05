package com.medicalsystem.excel

import com.medicalsystem.TransactionalDatabaseClearableTest
import com.medicalsystem.excel.importer.ExcelImporter
import com.medicalsystem.excel.importer.result.ImportError
import com.medicalsystem.init.TemplateInitializer
import com.medicalsystem.service.FieldValueService
import com.medicalsystem.service.FormService
import com.medicalsystem.service.PatientService
import org.junit.Before
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired

class ExcelImporterTest : TransactionalDatabaseClearableTest() {

    private val TEST_EXCEL_FILE = "data/baza2_test.xlsx"

    @Autowired
    lateinit var excelImporter: ExcelImporter

    @Autowired
    lateinit var formService: FormService

    @Autowired
    lateinit var fieldValueService: FieldValueService

    @Autowired
    lateinit var patientService: PatientService

    @Autowired
    lateinit var templateInitializer: TemplateInitializer

    @Before
    fun setUp() {
        templateInitializer.loadTemplateFromConfig()
    }

    override fun clearDatabase() {
        patientService.deleteAll()
        fieldValueService.deleteAll()
        formService.deleteAll()
    }

    @Test
    fun test() {
        val errors: List<ImportError> = excelImporter.importToDatabase(TEST_EXCEL_FILE)

        errors.forEach {
            println(it)
        }

        println(errors.size)
    }
}