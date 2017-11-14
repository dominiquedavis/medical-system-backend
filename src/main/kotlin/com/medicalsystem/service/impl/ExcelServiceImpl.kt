package com.medicalsystem.service.impl

import com.medicalsystem.excel.exporter.ExcelExporter
import com.medicalsystem.excel.importer.WorkbookImporter
import com.medicalsystem.service.ExcelService
import com.medicalsystem.util.ExcelUtils
import com.medicalsystem.util.FileUtils
import org.apache.poi.ss.usermodel.Workbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.IOException

@Service
class ExcelServiceImpl @Autowired constructor(
        val workbookImporter: WorkbookImporter,
        val excelExporter: ExcelExporter) : ExcelService {

    private val IMPORT_FILE_PATH = "data/dataImport.xlsx"
    private val EXPORT_FILE_PATH = "data/dataExport.xlsx"

    override fun importToDatabase(file: MultipartFile): ResponseEntity<*> =
            try {
                FileUtils.saveFile(file, IMPORT_FILE_PATH)
                importToDatabase(IMPORT_FILE_PATH)
                ResponseEntity.ok().body("File imported")
            } catch (e: IOException) {
                ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.message)
            }

    override fun exportToFile(): ResponseEntity<*> {
        TODO("not implemented")
    }

    override fun importToDatabase(path: String) {
        val workbook: Workbook? = ExcelUtils.loadWorkbook(path)
        workbook?.let {
            workbookImporter.importToDatabase(workbook)
        }
    }

    override fun exportToFile(path: String) =
            excelExporter.exportToFile(path)
}