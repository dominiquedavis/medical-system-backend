package com.medicalsystem.executor

import com.medicalsystem.domain.report.Report
import com.medicalsystem.domain.report.ReportCount
import com.medicalsystem.domain.report.ReportField
import com.medicalsystem.domain.report.ReportResults
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.Form
import org.springframework.stereotype.Component

@Component
class ReportExecutor {

    fun executeReport(report: Report, sortField: Field?, includedForms: List<Form>): ReportResults {
        // Get ReportFields which should be included in the report
        val includedFields: List<ReportField> = report.sections.filter { it.checked }
                .flatMap { it.fields }.filter { it.isChecked }

        // Create ReportCount for each Form
        val resultInfo: List<ReportCount> = includedForms.map { form ->
            val reportCount = ReportCount(formType = form.name)

            // Mock up
            reportCount.patientsFound = form.hashCode() % 100

            reportCount
        }

        return ReportResults(resultInfo = resultInfo)
    }
}