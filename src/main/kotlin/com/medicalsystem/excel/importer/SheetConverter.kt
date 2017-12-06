package com.medicalsystem.excel.importer

import com.medicalsystem.converter.BasicConverter
import com.medicalsystem.domain.template.Form
import com.medicalsystem.service.FormService
import org.apache.poi.ss.usermodel.Sheet
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import javax.persistence.EntityNotFoundException

@Component
class SheetConverter(private val formService: FormService) : BasicConverter<Sheet, MySheet> {

    @Transactional
    override fun convert(from: Sheet): MySheet {
        val sheetIndex: Int = from.workbook.getSheetIndex(from)
        val form: Form = formService.getBySheetIndex(sheetIndex)
                ?: throw EntityNotFoundException("No form with given sheet index: $sheetIndex")
        return MySheet(from, form)
    }
}