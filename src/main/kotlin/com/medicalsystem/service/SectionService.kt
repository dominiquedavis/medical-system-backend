package com.medicalsystem.service

import com.medicalsystem.domain.template.Section
import com.medicalsystem.repository.template.SectionRepository
import org.springframework.stereotype.Service

@Service
class SectionService(private val sectionRepository: SectionRepository) : CRUDService<Section, Long>(sectionRepository)