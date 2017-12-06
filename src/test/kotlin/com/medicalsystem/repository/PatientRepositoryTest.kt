package com.medicalsystem.repository

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.template.Form
import com.medicalsystem.repository.template.FormRepository
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class PatientRepositoryTest {

    @Autowired
    lateinit var patientRepository: PatientRepository
    @Autowired
    lateinit var formRepository: FormRepository

    @Before
    fun setup() = clearDatabase()
    @After
    fun tearDown() = clearDatabase()

    private fun clearDatabase() {
        patientRepository.deleteAll()
        formRepository.deleteAll()
    }

    @Test
    fun testCRUD() {
        val form = Form(name = "Test form", sheetIndex = 4324234)
        val patient1 = Patient("123")
        val patient2 = Patient("456")

        patient1.form = form
        patient2.form = form

        val savedForm = formRepository.save(form)
        val savedPatient1 = patientRepository.save(patient1)
        val savedPatient2 = patientRepository.save(patient2)

        val patient1Queried = patientRepository.getOne(savedPatient1.id)
        val patient2Queried = patientRepository.getOne(savedPatient2.id)

        assertEquals(savedForm, patient1Queried.form)
        assertEquals(savedForm, patient2Queried.form)
    }
}