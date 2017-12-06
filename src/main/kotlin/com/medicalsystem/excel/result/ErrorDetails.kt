package com.medicalsystem.excel.result

data class ErrorDetails(
        val sheet: Int? = null,
        val row: Int? = null,
        val cell: Int? = null,
        val message: String = "",
        val sheetImported: Boolean = false,
        val rowImported: Boolean = false,
        val cellImported: Boolean = false
)