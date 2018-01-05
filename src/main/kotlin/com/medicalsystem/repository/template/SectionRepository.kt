package com.medicalsystem.repository.template

import com.medicalsystem.domain.template.Section
import org.springframework.data.jpa.repository.JpaRepository

interface SectionRepository : JpaRepository<Section, Long>