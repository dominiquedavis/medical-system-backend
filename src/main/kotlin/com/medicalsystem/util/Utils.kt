package com.medicalsystem.util

import com.medicalsystem.domain.template.ValueOption
import org.apache.log4j.Logger
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.DataFormatter

fun <T : Any> T.logger(): Logger = Logger.getLogger(this.javaClass)

private val formatter = DataFormatter()

fun Cell.getValueAsString(): String = formatter.formatCellValue(this).trim()

fun Set<ValueOption>.containsKey(key: String): Boolean = this.any { it.key == key }

fun Set<ValueOption>.getByKey(key: String): ValueOption = this.first { it.key == key }