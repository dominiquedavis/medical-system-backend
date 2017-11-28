package com.medicalsystem.repository

import com.medicalsystem.model.report.Report
import org.springframework.data.jpa.repository.JpaRepository

interface ReportRepository : JpaRepository<Report, Long>