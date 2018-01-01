package com.medicalsystem.excel.result

data class ErrorDetails(
        val sheet: String? = null,
        val row: Int? = null,
        val cell: String? = null,
        val message: String = "",
        val sheetImported: Boolean = false,
        val rowImported: Boolean = false,
        val cellImported: Boolean = false
)