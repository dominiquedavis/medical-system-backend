package com.medicalsystem.domain.report

data class ReportResults(
        var resultInfo: MutableList<ReportCount> = mutableListOf(),
        var urlToBlob: String = ""
)