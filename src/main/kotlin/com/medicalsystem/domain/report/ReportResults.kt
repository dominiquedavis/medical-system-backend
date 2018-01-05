package com.medicalsystem.domain.report

data class ReportResults(var resultInfo: MutableList<ReportCount> = mutableListOf()) {
    val urlToBlob: String = "api/reportResult"
}