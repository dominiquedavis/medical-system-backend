package com.medicalsystem.controller

import com.medicalsystem.domain.dto.FormDTO
import com.medicalsystem.domain.report.Report
import com.medicalsystem.domain.report.ReportResults
import com.medicalsystem.service.ReportService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/reports")
class ReportController(private val reportService: ReportService) {

    /**
     * Returns a list of all saved reports.
     */
    @GetMapping
    fun getReports(): List<Report> =
            reportService.findAll()

    /**
     * Returns common part of the listed forms as a FormDTO object.
     */
    @GetMapping("form/{formNames}")
    fun getReportBaseForm(@PathVariable formNames: String): FormDTO {
        val names: List<String> = formNames.split(",").filterNot { it.isBlank() }
        return reportService.getReportBaseForm(names)
    }

    /**
     * Persist given Report to the database.
     */
    @PostMapping
    fun saveReport(@RequestBody report: Report): Report =
            reportService.save(report)

    /**
     * Updates Report at a given ID.
     */
    @PostMapping("{reportId}")
    fun updateReport(@PathVariable reportId: Long, @RequestBody report: Report): Report =
            reportService.updateReport(reportId, report)

    /**
     * Executes a given Report.
     */
    @PostMapping("execute")
    fun executeReport(@RequestBody report: Report): ReportResults =
            reportService.execute(report)

    /**
     * Returns result of the last executed report as an excel file.
     */
    @GetMapping("api/reportResult")
    fun getResultExcelBlob(): ResponseEntity<*> =
            TODO()
}