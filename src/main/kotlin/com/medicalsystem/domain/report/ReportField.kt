package com.medicalsystem.domain.report

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.ElementCollection
import javax.persistence.Entity

@Entity
class ReportField(
        var fieldID: Long = 0,
        var conditionType: ConditionType = ConditionType.EQUAL,

        @ElementCollection
        var conditionValue: List<String> = emptyList()

) : LongIdComparableEntity()