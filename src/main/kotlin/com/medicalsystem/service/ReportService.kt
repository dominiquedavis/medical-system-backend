package com.medicalsystem.service

import com.medicalsystem.domain.report.Report
import com.medicalsystem.domain.report.ReportField
import com.medicalsystem.domain.report.ReportResults
import com.medicalsystem.domain.report.ReportSection
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.Form
import com.medicalsystem.domain.template.Section
import com.medicalsystem.exception.NO_FORM_WITH_NAME
import com.medicalsystem.executor.ReportExecutor
import com.medicalsystem.repository.report.ReportRepository
import org.springframework.stereotype.Service
import javax.persistence.EntityNotFoundException

@Service
class ReportService(
        reportRepository: ReportRepository,
        private val formService: FormService,
        private val fieldService: FieldService,
        private val reportExecutor: ReportExecutor
) : CRUDService<Report, Long>(reportRepository) {

    /**
     * Accepts an empty Report with information about forms and fills the Report with sections and fields.
     */
    fun createReport(report: Report): Report {
        // Get forms which should be included in report
        val includedForms: List<Form> = report.includedForms
                .map { formService.findByName(it) ?: throw EntityNotFoundException(NO_FORM_WITH_NAME + it) }

        // Fill report with common part of all of the included forms
        fillWithCommonPart(report, includedForms)

        // Return persisted object
        return save(report)
    }

    /**
     * Updates a Report at a given ID.
     */
    fun updateReport(reportId: Long, report: Report): Report {
        report.id = reportId
        save(report)
        return report
    }

    /**
     * Executes a given Report.
     */
    fun execute(report: Report): ReportResults {
        // Fetch Field to sort by
        val sortField: Field? = getSortField(report)

        // Get Forms which should be included in the report
        val includedForms: List<Form> = report.includedForms.map {
            formService.findByName(it) ?:
                    throw  EntityNotFoundException(NO_FORM_WITH_NAME + it)
        }

        return reportExecutor.executeReport(report, sortField, includedForms)
    }

    /**
     * Fills report with common part of all of the included forms
     */
    private fun fillWithCommonPart(report: Report, forms: List<Form>) {
        // Get common sections
        val commonSections: List<Section> = getCommonSections(forms)

        // Get common fields
        val commonFields: List<Field> = getCommonFields(forms)

        // Create ReportSections and assign to Report
        val reportSections: List<ReportSection> = commonSections.map {
            val reportSection = ReportSection(name = it.name)

            // Add ReportFields to ReportSection
            val reportFields = commonFields.filter { it.section?.name == reportSection.name }
                    .map {
                        val reportField = ReportField()
                        reportField.formField = it.id
                        reportField
                    }

            reportSection.fields = reportFields.toMutableList()

            reportSection
        }

        report.sections = reportSections
    }

    /**
     * Returns common sections of the given forms
     */
    private fun getCommonSections(forms: List<Form>): List<Section> {
        val allSections: List<Section> = forms.flatMap { it.sections }
        return allSections.filter { it.presentInEveryForm(forms) }.distinctBy { it.name }
    }

    /**
     * Checks if section is present in every form (identified by name)
     */
    private fun Section.presentInEveryForm(forms: List<Form>): Boolean {
        for (form in forms) {
            if (!form.sections.any { it.name == this.name }) {
                return false
            }
        }
        return true
    }

    /**
     * Returns common fields of the given forms
     */
    private fun getCommonFields(forms: List<Form>): List<Field> {
        val allFields: List<Field> = forms.flatMap { it.sections }.flatMap { it.fields }
        return allFields.filter { it.presentInEveryForm(forms) }.distinctBy { it.name }
    }

    /**
     * Checks if section is preset in every form (identified by name)
     */
    private fun Field.presentInEveryForm(forms: List<Form>): Boolean {
        for (form in forms) {
            if (!form.sections.flatMap { it.fields }.any { it.name == this.name }) {
                return false
            }
        }
        return true
    }

    private fun getSortField(report: Report): Field? {
        val reportFieldId: Long = report.sortByField
        val reportField: ReportField? = report.sections.flatMap { it.fields }.find { it.id == reportFieldId }
        reportField?.let {
            val fieldId = it.formField
            return fieldService.findByID(fieldId)
        }
        return null
    }
}