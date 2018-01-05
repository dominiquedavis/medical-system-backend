package com.medicalsystem.domain.report

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.medicalsystem.domain.id.LongIdComparableEntity
import com.medicalsystem.serializer.FieldDeserializer
import com.medicalsystem.serializer.FieldSerializer
import javax.persistence.ElementCollection
import javax.persistence.Entity

@Entity
class ReportField(
        var checked: Boolean = false,

        @JsonSerialize(using = FieldSerializer::class)
        @JsonDeserialize(using = FieldDeserializer::class)
        var field: Long = 0,

        var conditionType: ConditionType = ConditionType.EQUAL,

        @ElementCollection
        var conditionValue: List<String> = emptyList()

) : LongIdComparableEntity()