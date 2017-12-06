package com.medicalsystem.util

import org.apache.commons.lang3.time.DateUtils
import java.time.LocalDate
import java.time.ZoneId
import java.util.*

object DateUtils {

    private val dateFormats = arrayOf(
            "yyyy-MM-dd",
            "d.M.y",
            "M/d/y"
    )

    fun fromString(s: String): LocalDate {
        val utilDate: Date = DateUtils.parseDateStrictly(s, *dateFormats)
        return toLocalDate(utilDate)
    }

    fun toLocalDate(utilDate: Date): LocalDate = utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()
}