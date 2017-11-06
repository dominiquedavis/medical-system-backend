package com.medicalsystem.service.impl

import com.medicalsystem.repository.ApplicationUserRepository
import com.medicalsystem.service.ApplicationUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ApplicationUserServiceImpl @Autowired constructor(
        applicationUserRepository: ApplicationUserRepository) : ApplicationUserService(applicationUserRepository)