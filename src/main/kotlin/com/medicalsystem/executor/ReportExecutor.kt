package com.medicalsystem.executor

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.report.*
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.Form
import com.medicalsystem.domain.value.FieldValue
import com.medicalsystem.exception.NO_FIELD_VALUE_FOR_FIELD_AND_PATIENT
import com.medicalsystem.exception.NO_FIELD_WITH_ID
import com.medicalsystem.service.FieldService
import com.medicalsystem.service.FieldValueService
import com.medicalsystem.service.PatientService
import org.springframework.stereotype.Component
import javax.persistence.EntityNotFoundException

@Component
class ReportExecutor(
        private val patientService: PatientService,
        private val fieldService: FieldService,
        private val fieldValueService: FieldValueService,
        private val excelReportGenerator: ExcelReportGenerator
) {

    fun executeReport(report: Report, sortField: Field?, includedForms: List<Form>): ReportResults {
        // Get ReportFields which should be included in the report
        val reportFields: List<ReportField> = report.sections
                .flatMap { it.fields }.filter { it.isChecked }

        val formToPatientsMeetingCriteria = mutableMapOf<Form, List<Patient>>()

        // Create ReportCount for each Form
        val resultInfo: List<ReportCount> = includedForms.map { form ->
            val patientsMeetingCriteria = findPatientsMeetingCriteria(form, reportFields)

            println("Report fields:")
            reportFields.forEach { println(it.conditionValue) }

            // TODO: Excel here
            patientsMeetingCriteria.forEach { println(it) }
            formToPatientsMeetingCriteria.put(form, patientsMeetingCriteria)

            ReportCount(formType = form.name, patientsFound = patientsMeetingCriteria.size)
        }

        // Set data in ExcelReportGenerator (to generate an excel file later)
        excelReportGenerator.excelReportInfo = ExcelReportInfo(formToPatientsMeetingCriteria, reportFields, sortField)

        return ReportResults(resultInfo = resultInfo)
    }

    private fun findPatientsMeetingCriteria(form: Form, reportFields: List<ReportField>): List<Patient> {
        val fieldsMap: Map<ReportField, Field> = reportFields
                .map { it to (fieldService.findByID(it.formField) ?: throw EntityNotFoundException(NO_FIELD_WITH_ID + it.formField)) }
                .toMap()

        // Get all patients
        val allPatients: List<Patient> = patientService.findAllByForm(form)

        // Filter out patient not meeting criteria
        return allPatients.filter { it.meetsCriteriaForAllFields(fieldsMap) }
    }

    private fun Patient.meetsCriteriaForAllFields(fieldsMap: Map<ReportField, Field>): Boolean {
        return fieldsMap.all { this.meetsCriteriaForField(it.key, it.value) }
    }

    private fun Patient.meetsCriteriaForField(reportField: ReportField, field: Field): Boolean {
        val fieldValue: FieldValue<*> = fieldValueService.findByFieldAndPatientByNameNoCreate(field.name, this) ?:
                throw EntityNotFoundException(NO_FIELD_VALUE_FOR_FIELD_AND_PATIENT + "$field $this")

        return fieldValue.meetsCriteria(reportField)
    }
}