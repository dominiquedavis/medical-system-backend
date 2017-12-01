package com.medicalsystem.init

import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class InitialConfigTest {

    @Autowired
    lateinit var initialConfig: InitialConfig

    @Test
    fun testAutowire() = assertNotNull(initialConfig)

    @Test
    fun testBindings() {
        assertNotNull(initialConfig.forms)
        assertFalse(initialConfig.forms.isEmpty())

        initialConfig.forms.forEach {
            assertNotNull(it)
            assertNotNull(it.name)
            assertNotNull(it.type)
            assertNotNull(it.sections)
            assertFalse(it.sections.isEmpty())
            it.sections.forEach {
                assertNotNull(it)
                assertNotNull(it.fields)
                assertFalse(it.fields.isEmpty())
                it.fields.forEach {
                    assertNotNull(it.name)
                    assertNotNull(it.type)
                    assertNotNull(it.options)
                    it.options.forEach {
                        assertNotNull(it)
                        assertNotNull(it.key)
                        assertNotNull(it.`val`)
                    }
                }
            }
        }
    }
}