package com.medicalsystem.repository

import com.medicalsystem.model.Patient
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
class PatientRepositoryTest {

    @Autowired
    lateinit var patientRepository: PatientRepository

    @Before @After
    fun tearDown() = patientRepository.deleteAll()

    @Test
    fun testAutowire() {
        Assert.assertNotNull(patientRepository)
    }

    @Test
    fun testSave() {
        val patient = Patient(id = "12389")
        val savedPatient = patientRepository.save(patient)

        Assert.assertNotNull(savedPatient)
    }
}