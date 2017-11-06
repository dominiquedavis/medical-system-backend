package com.medicalsystem.excel.importer

import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Workbook
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class WorkbookImporter @Autowired constructor(val sheetImporter: SheetImporter) {

    fun importToDatabase(workbook: Workbook) {

        (0 until workbook.numberOfSheets)
                .map { workbook.getSheetAt(it) }
                .forEach {
                    try {
                        sheetImporter.importToDatabase(it)
                    } catch (e: ExcelImportException) {
                        logger().error(e.message)
                        logger().warn("Skipping sheet '${it.sheetName}'")
                    }
                }

        logger().info("Import completed")
    }
}