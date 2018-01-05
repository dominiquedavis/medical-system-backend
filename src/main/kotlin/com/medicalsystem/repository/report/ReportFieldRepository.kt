package com.medicalsystem.repository.report

import com.medicalsystem.domain.report.ReportField
import org.springframework.data.jpa.repository.JpaRepository

interface ReportFieldRepository : JpaRepository<ReportField, Long>