package com.medicalsystem.util

import org.apache.commons.lang3.time.DateUtils
import java.util.*

object DateUtils {

    private val dateFormats: Array<String> = arrayOf(
            "d.M.y",
            "M/d/y"
    )

    fun fromString(s: String): Date =
            DateUtils.parseDateStrictly(s, *dateFormats)
}