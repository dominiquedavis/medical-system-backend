package com.medicalsystem.model.report

import javax.persistence.*

@Entity
data class ReportField(
    @Id @GeneratedValue
    var id: Long = 0,
    var fieldID: Long = 0,
    var conditionType: ConditionType? = null,
    @ElementCollection
    var conditionValue: List<String> = emptyList()
)