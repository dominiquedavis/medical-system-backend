package com.medicalsystem.controller

import com.medicalsystem.excel.exporter.ExcelExporter
import com.medicalsystem.excel.exporter.TemplateExporter
import com.medicalsystem.excel.importer.ExcelImporter
import com.medicalsystem.excel.importer.result.ImportError
import com.medicalsystem.excel.importer.result.ImportResult
import com.medicalsystem.util.logger
import org.springframework.core.io.InputStreamResource
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.*

@RestController
class ExcelController(private val excelImporter: ExcelImporter, private val excelExporter: ExcelExporter,
                      private val templateExporter: TemplateExporter) {

    private val IMPORT_FILE_PATH = "data/dataImport.xlsx"
    private val EXPORT_FILE_PATH = "data/dataExport.xlsx"

    /**
     * Imports a file to the database.
     *
     * @param file a MultipartFile object representing the file to import
     */
    @PostMapping
    fun importToDatabase(@RequestParam("file") file: MultipartFile): ImportResult =
            try {
                file.save(IMPORT_FILE_PATH)
                ImportResult(excelImporter.importToDatabase(IMPORT_FILE_PATH))
            } catch (e: IOException) {
                throw e
            }

    /**
     * Exports current database to an excel file.
     */
    @GetMapping("api/export")
    fun exportToFile(): ResponseEntity<*> {
        excelExporter.exportToFile(EXPORT_FILE_PATH)
        val file = File(EXPORT_FILE_PATH)
        val resource = InputStreamResource(FileInputStream(file))

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource)
    }

    /**
     * Provides template excel file.
     */
    @GetMapping("api/template")
    fun exportTemplate(): ResponseEntity<*> {
        templateExporter.exportTemplate(EXPORT_FILE_PATH)
        val file = File(EXPORT_FILE_PATH)
        val resource = InputStreamResource(FileInputStream(file))

        return ResponseEntity.ok()
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource)
    }

    private fun MultipartFile.save(path: String) {
        if (this.isEmpty)
            throw IOException("File is empty")

        BufferedOutputStream(FileOutputStream(File(path))).use {
            it.write(this.bytes)
            logger().info("File saved to '$path'")
        }
    }
}