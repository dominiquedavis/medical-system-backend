package com.medicalsystem.model.value

import com.medicalsystem.model.report.ReportField
import org.apache.poi.ss.usermodel.Cell
import javax.persistence.*

@Entity
@Table(name = "TEXT_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class TextFieldValue : FieldValue<String>() {

    @Column(name = "VALUE")
    override var value: String = ""

    override fun setValueFromString(s: String) {
        this.value = s
    }

    override fun createCellValue(cell: Cell) {
        cell.setCellValue(value)
    }

    override fun fullfills(reportField: ReportField): Boolean {
        return false
    }
}