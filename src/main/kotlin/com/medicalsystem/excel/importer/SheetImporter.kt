package com.medicalsystem.excel.importer

import com.medicalsystem.domain.template.Form
import com.medicalsystem.excel.importer.result.ImportError
import com.medicalsystem.exception.NO_FORM_WITH_SHEET_NAME
import com.medicalsystem.service.FormService
import org.apache.poi.ss.usermodel.Sheet
import org.springframework.stereotype.Component
import javax.persistence.EntityNotFoundException

@Component
class SheetImporter(private val rowImporter: RowImporter, private val formService: FormService) {

    fun importSheet(sheet: Sheet): List<ImportError> {
        val formSheet: FormSheet = sheet.bindToForm()

        if (formSheet.containsNoDataRows) {
            return listOf(ImportError.noDataRowsInSheet(formSheet))
        }

        // Iterate over rows
        return formSheet.rowIterator().asSequence()
                // Skip header rows
                .drop(formSheet.numberOfHeaderRows)
                // Import each row
                .map { rowImporter.importRow(it, formSheet) }
                // Aggregate import results
                .flatten().toList()
    }

    private fun Sheet.bindToForm(): FormSheet {
        val form: Form = formService.findBySheetName(sheetName) ?:
                throw EntityNotFoundException(NO_FORM_WITH_SHEET_NAME + sheetName)

        return FormSheet(this, form)
    }
}