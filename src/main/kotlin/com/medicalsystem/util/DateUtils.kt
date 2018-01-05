package com.medicalsystem.util

import org.apache.commons.lang3.time.DateUtils
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.*

private val dateFormats = arrayOf(
        "yyyy-MM-dd",
        "d.M.y",
        "M/d/y"
)

private val jsonFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

fun stringToLocalDate(s: String): LocalDate {
    val utilDate: Date = DateUtils.parseDateStrictly(s, *dateFormats)
    return utilToLocalDate(utilDate)
}

fun utilToLocalDate(utilDate: Date): LocalDate =
        utilDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

fun LocalDate.toUtilDate(): Date =
        Date.from(this.atStartOfDay(ZoneId.systemDefault()).toInstant())

fun LocalDate.toJsonString(): String =
        this.format(jsonFormatter)