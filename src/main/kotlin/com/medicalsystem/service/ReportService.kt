package com.medicalsystem.service

import com.medicalsystem.domain.dto.FormDTO
import com.medicalsystem.domain.report.Report
import com.medicalsystem.domain.report.ReportResults
import com.medicalsystem.repository.report.ReportRepository
import org.springframework.stereotype.Service

@Service
class ReportService(reportRepository: ReportRepository) : CRUDService<Report, Long>(reportRepository) {

    fun getReportBaseForm(formNames: List<String>): FormDTO {
        TODO()
    }

    fun updateReport(reportId: Long, report: Report): Report {
        report.id = reportId
        save(report)
        return report
    }

    fun execute(report: Report): ReportResults {
        TODO("not implemented")
    }
}