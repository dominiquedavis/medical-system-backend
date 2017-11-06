package com.medicalsystem.repository

import com.medicalsystem.model.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationUserRepository : JpaRepository<ApplicationUser, Long>