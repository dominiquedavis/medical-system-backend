package com.medicalsystem.repository

import com.medicalsystem.model.report.ReportField
import org.springframework.data.jpa.repository.JpaRepository

interface ReportFieldRepository : JpaRepository<ReportField, Long>