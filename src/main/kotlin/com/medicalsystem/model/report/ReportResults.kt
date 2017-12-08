package com.medicalsystem.model.report

data class ReportResults(
        var resultInfo: MutableList<ReportCount> = mutableListOf(),
        var urlToBlob: String = ""
)