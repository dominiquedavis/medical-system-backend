package com.medicalsystem.service.impl

import com.medicalsystem.model.ApplicationUser
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
class ApplicationUserServiceImplTest {

    @Autowired
    lateinit var applicationUserService: ApplicationUserServiceImpl

    @Before @After
    fun tearDown() = applicationUserService.deleteAll()

    @Test
    fun testAutowire() {
        Assert.assertNotNull(applicationUserService)
    }

    @Test
    fun testSave() {
        val user = ApplicationUser(username = "fifi")
        val saved = applicationUserService.save(user)
        Assert.assertTrue(saved.id > 0)
    }

    @Test
    fun testRegister() {
        val user = ApplicationUser(username = "fifi", email = "fifi@gmail.com")
        Assert.assertTrue(applicationUserService.register(user))
        Assert.assertFalse(applicationUserService.register(user))
    }
}