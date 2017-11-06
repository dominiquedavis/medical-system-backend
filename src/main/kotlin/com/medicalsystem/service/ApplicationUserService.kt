package com.medicalsystem.service

import com.medicalsystem.model.ApplicationUser
import com.medicalsystem.repository.ApplicationUserRepository

abstract class ApplicationUserService(applicationUserRepository: ApplicationUserRepository)
    : DefaultCRUDService<ApplicationUser, Long, ApplicationUserRepository>(applicationUserRepository)