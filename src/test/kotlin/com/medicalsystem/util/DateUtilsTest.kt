package com.medicalsystem.util

import com.medicalsystem.BasicTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import java.time.LocalDate

@RunWith(Parameterized::class)
class DateUtilsTest(
        private val dateString: String,
        private val d: Int,
        private val m: Int,
        private val y: Int
) : BasicTest() {

    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun parameters(): Collection<Array<Any>> =
                listOf(
                        arrayOf("05.12.2016", 5, 12, 2016),
                        arrayOf("17.01.2018", 17, 1, 2018),
                        arrayOf("11/01/2018", 1, 11, 2018),
                        arrayOf("2018-01-17", 17, 1, 2018)
                )
    }

    @Test
    fun test() {
        val localDate: LocalDate = stringToLocalDate(dateString)
        assertEquals(d, localDate.dayOfMonth)
        assertEquals(m, localDate.monthValue)
        assertEquals(y, localDate.year)
    }
}