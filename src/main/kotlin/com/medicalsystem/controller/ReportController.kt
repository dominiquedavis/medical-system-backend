package com.medicalsystem.controller

import com.medicalsystem.factory.FormDTOFactory
import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.model.report.Report
import com.medicalsystem.repository.ReportRepository
import com.medicalsystem.service.FormService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/reports")
class ReportController @Autowired constructor(
    val reportRepository: ReportRepository,
    val formService: FormService,
    val formDTOFactory: FormDTOFactory) {

    @GetMapping
    fun getReports(): List<Report> =
        reportRepository.findAll()

    @GetMapping("form/{formIds}")
    fun getReportBaseForm(@PathVariable formIds: String): FormDTO {
        val forms: List<FormDTO> = formIds.split(",")
            .map { formService.getById(it.toLong()) ?: throw RuntimeException("Form not found") }
            .map { formDTOFactory.toDTO(it, null) }

        //val commonForm = FormDTO(type = "common", sections = forms[0].sections)
        return forms[0]
    }

    @PostMapping
    fun saveReport(@RequestBody report: Report): Report =
        reportRepository.save(report)

    @PostMapping("{reportId}")
    fun updateReport(@PathVariable reportId: Long, @RequestBody report: Report): Report {
        report.id = reportId
        reportRepository.save(report)
        return report
    }
}