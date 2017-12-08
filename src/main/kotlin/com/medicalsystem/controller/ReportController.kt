package com.medicalsystem.controller

import com.medicalsystem.factory.FormDTOFactory
import com.medicalsystem.model.Field
import com.medicalsystem.model.Form
import com.medicalsystem.model.Patient
import com.medicalsystem.model.dto.FormDTO
import com.medicalsystem.model.report.ConditionType
import com.medicalsystem.model.report.Report
import com.medicalsystem.model.report.ReportCount
import com.medicalsystem.model.report.ReportResults
import com.medicalsystem.model.value.FieldValue
import com.medicalsystem.repository.ReportRepository
import com.medicalsystem.service.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/reports")
class ReportController @Autowired constructor(
    val reportRepository: ReportRepository,
    val formService: FormService,
    val formDTOFactory: FormDTOFactory,
    val fieldService: FieldService,
    val patientService: PatientService,
    val fieldValueService: FieldValueService,
    val excelService: ExcelService) {

    @GetMapping
    fun getReports(): List<Report> =
        reportRepository.findAll()

    @GetMapping("form/{formName}")
    fun getReportBaseForm(@PathVariable formName: String): FormDTO {
        //val forms: List<FormDTO> = formName.split(",")
         //   .map { formService.findByName(it) ?: throw RuntimeException("Form not found") }
        val forms = formService.getAll()
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

    @PostMapping("execute")
    fun executeReport(@RequestBody report: Report): ReportResults {
        val forms: List<Form> = report.includedForms.mapNotNull { formService.findByName(it) }
        val fields: List<Field> = fieldService.getAllByForm(forms[0]).filter { report.fields.any { rf -> it.id == rf.id } }
        val patients = patientService.getAllByForm(forms[0])

        val valuesMap: MutableMap<Patient, List<FieldValue<*>>> = mutableMapOf()

        for (patient in patients) {
            val fieldValues: List<FieldValue<*>> = fieldValueService.getAllFieldValuesForPatient(patient)
                    .filter { fields.contains(it.field) }

            val filteredValues: MutableList<FieldValue<*>> = mutableListOf()
            for (reportField in report.fields) {
                val fieldValue = fieldValues.first { it.field?.id == reportField.fieldID }

                if (fieldValue.fullfills(reportField)) {
                    filteredValues.add(fieldValue)
                }
            }
            valuesMap.put(patient, fieldValues)
        }

        println("GENERATED MAP: ${valuesMap.size}")

        ///////////////

        return ReportResults(
                resultInfo = mutableListOf(ReportCount(formType = "OPEN", patientsFound = 34), ReportCount(formType = "EVAR", patientsFound = 78)),
                urlToBlob = "api/reportResult"
        )
    }

    @GetMapping("api/reportResult")
    fun getBlob(): ResponseEntity<*> =
            excelService.exportToFile()
}