package com.medicalsystem.model.value

import com.medicalsystem.model.report.ReportField
import com.medicalsystem.util.DateUtils
import com.medicalsystem.util.ExcelUtils
import com.medicalsystem.util.logger
import org.apache.poi.ss.usermodel.Cell
import java.text.ParseException
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "DATE_FIELD_VALUES")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
class DateFieldValue : FieldValue<Date?>() {

    @Column(name = "VALUE")
    override var value: Date? = Date()

    override fun setValueFromString(s: String) {
        this.value =
                try {
                    DateUtils.fromString(s)
                } catch (e: ParseException) {
                    null
                }
    }

    override fun createCellValue(cell: Cell) {
        ExcelUtils.setDateCellStyle(cell)

        if (value == null) {
            cell.setCellValue("")
            logger().error("Date is null: '${field?.name}'")
        } else {
            cell.setCellValue(value)
        }
    }

    override fun fullfills(reportField: ReportField): Boolean {
        return false
    }
}