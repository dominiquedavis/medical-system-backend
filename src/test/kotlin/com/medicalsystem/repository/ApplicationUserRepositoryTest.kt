package com.medicalsystem.repository

import com.medicalsystem.model.ApplicationUser
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.test.context.junit4.SpringRunner
import kotlin.test.assertFailsWith

@RunWith(SpringRunner::class)
@SpringBootTest
class ApplicationUserRepositoryTest {

    @Autowired
    lateinit var applicationUserRepository: ApplicationUserRepository

    @Before @After
    fun tearDown() = applicationUserRepository.deleteAll()

    @Test
    fun testAutowire() {
        Assert.assertNotNull(applicationUserRepository)
    }

    @Test
    fun testSave() {
        val user = ApplicationUser(username = "msieniawski", password = "pw123")
        val savedUser = applicationUserRepository.save(user)
        Assert.assertTrue(applicationUserRepository.exists(savedUser.id))
    }

    @Test
    fun testPersistSameUsernamesShouldThrowAnException() {
        val user1 = ApplicationUser(username = "msieniawski", password = "pw123")
        val user2 = ApplicationUser(username = "msieniawski", password = "pw123")

        applicationUserRepository.save(user1)

        assertFailsWith(DataIntegrityViolationException::class) {
            applicationUserRepository.save(user2)
        }
    }
}