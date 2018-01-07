package com.medicalsystem.domain.report

import com.medicalsystem.domain.Patient
import com.medicalsystem.domain.template.Field
import com.medicalsystem.domain.template.Form

/**
 * A wrapper class for passing parameters
 */
data class ExcelReportInfo(
        val formsToPatientsMeetingCriteria: Map<Form, List<Patient>>,
        val includedReportFields: List<ReportField>,
        val sortField: Field?
)