package com.medicalsystem.domain.report

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.OneToMany

@Entity
class ReportSection(
        var name: String = "",
        var checked: Boolean = false,

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
        var fields: List<ReportField> = emptyList()

) : LongIdComparableEntity()