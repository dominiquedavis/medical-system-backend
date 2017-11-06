package com.medicalsystem.service.impl

import com.medicalsystem.excel.importer.WorkbookImporter
import com.medicalsystem.service.ExcelService
import com.medicalsystem.util.ExcelUtils
import org.apache.poi.ss.usermodel.Workbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExcelServiceImpl @Autowired constructor(val workbookImporter: WorkbookImporter) : ExcelService {

    override fun importToDatabase(path: String) {
        val workbook: Workbook? = ExcelUtils.loadWorkbook(path)
        workbook?.let {
            workbookImporter.importToDatabase(workbook)
        }
    }

    override fun exportToFile(path: String) {
        TODO("not implemented")
    }
}