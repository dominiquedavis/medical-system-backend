package com.medicalsystem.service.impl

import com.medicalsystem.repository.SectionRepository
import com.medicalsystem.service.SectionService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class SectionServiceImpl @Autowired constructor(sectionRepository: SectionRepository) : SectionService(sectionRepository)