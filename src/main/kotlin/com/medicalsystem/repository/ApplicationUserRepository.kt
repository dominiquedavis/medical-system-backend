package com.medicalsystem.repository

import com.medicalsystem.domain.ApplicationUser
import org.springframework.data.jpa.repository.JpaRepository

interface ApplicationUserRepository : JpaRepository<ApplicationUser, Long> {

    fun findByUsername(username: String): ApplicationUser?
}