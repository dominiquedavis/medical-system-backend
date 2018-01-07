package com.medicalsystem.domain.report

import com.medicalsystem.domain.id.LongIdComparableEntity
import javax.persistence.*

@Entity
class Report(
        var name: String? = "",

        var description: String? = "",

        @ElementCollection
        var includedForms: List<String> = emptyList(),

        @OneToMany(cascade = [ CascadeType.ALL ], orphanRemoval = true, fetch = FetchType.EAGER)
        var sections: List<ReportSection> = emptyList(),

        var sortByField: Long = 0

) : LongIdComparableEntity()