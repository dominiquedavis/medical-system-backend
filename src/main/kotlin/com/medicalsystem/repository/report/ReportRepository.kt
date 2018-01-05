package com.medicalsystem.repository.report

import com.medicalsystem.domain.report.Report
import org.springframework.data.jpa.repository.JpaRepository

interface ReportRepository : JpaRepository<Report, Long>