package com.medicalsystem.repository

import com.medicalsystem.model.Section
import org.springframework.data.jpa.repository.JpaRepository

interface SectionRepository : JpaRepository<Section, Long>