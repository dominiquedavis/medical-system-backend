package com.medicalsystem.service

import com.medicalsystem.model.Section
import com.medicalsystem.model.dto.SectionDTO
import com.medicalsystem.repository.SectionRepository

abstract class SectionService(sectionRepository: SectionRepository)
    : DefaultCRUDService<Section, Long, SectionRepository>(sectionRepository)