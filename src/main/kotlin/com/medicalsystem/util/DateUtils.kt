package com.medicalsystem.util

import org.apache.commons.lang3.time.DateUtils
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    private val dateFormats: Array<String> = arrayOf(
            "yyyy-MM-dd",
            "d.M.y",
            "M/d/y"
    )

    private val jsonFormatter = SimpleDateFormat("yyyy-MM-dd")

    fun fromString(s: String): Date = DateUtils.parseDateStrictly(s, *dateFormats)

    fun toString(date: Date?): String =
        if (date != null) {
            jsonFormatter.format(date)
        } else {
            ""
        }
}