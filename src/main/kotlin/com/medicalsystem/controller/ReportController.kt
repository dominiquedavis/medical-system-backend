package com.medicalsystem.controller

import com.medicalsystem.domain.report.Report
import com.medicalsystem.domain.report.ReportResults
import com.medicalsystem.executor.ExcelReportGenerator
import com.medicalsystem.service.ReportService
import org.springframework.core.io.InputStreamResource
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.io.File
import java.io.FileInputStream

@RestController
@RequestMapping("api/reports")
class ReportController(
        private val reportService: ReportService,
        private val excelReportGenerator: ExcelReportGenerator
) {

    /**
     * Returns a list of all saved reports.
     */
    @GetMapping
    fun getReports(): List<Report> =
            reportService.findAll()

    /**
     * Accepts an empty Report with information about forms and fills the Report with sections and fields.
     */
    @PostMapping
    fun createReport(@RequestBody report: Report): Report =
            reportService.createReport(report)

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
    @GetMapping("result")
    fun exportReport(): ResponseEntity<*> {
        excelReportGenerator.generateExcelFile("data/dataExport.xlsx")
        val file = File("data/dataExport.xlsx")
        val resource = InputStreamResource(FileInputStream(file))

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource)
    }
}