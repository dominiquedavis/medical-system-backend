package com.medicalsystem.converter

import java.sql.Date
import java.time.LocalDate
import javax.persistence.AttributeConverter
import javax.persistence.Converter

@Converter(autoApply = true)
class LocalDateAttributeConverter : AttributeConverter<LocalDate, Date> {

    override fun convertToDatabaseColumn(localDate: LocalDate?): Date? =
            localDate?.let { Date.valueOf(it) }

    override fun convertToEntityAttribute(sqlDate: Date?): LocalDate? =
            sqlDate?.let { sqlDate.toLocalDate() }
}