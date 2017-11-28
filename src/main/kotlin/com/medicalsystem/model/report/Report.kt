package com.medicalsystem.model.report

import javax.persistence.*

@Entity
data class Report(
    @Id @GeneratedValue
    var id: Long = 0,
    var name: String = "",
    var description: String = "",
    @ElementCollection
    var includedForms: List<String> = emptyList(),
    @OneToMany(cascade = arrayOf(CascadeType.ALL), orphanRemoval = true, fetch = FetchType.EAGER)
    var fields: List<ReportField> = emptyList(),
    var sortByFieldID: Long = 0
)