package com.medicalsystem.domain.report

data class ReportResults(var resultInfo: List<ReportCount> = emptyList()) {
    val urlToBlob: String = "api/reports/result"
}
