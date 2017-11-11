package com.medicalsystem.service

import com.medicalsystem.model.ApplicationUser
import com.medicalsystem.repository.ApplicationUserRepository

abstract class ApplicationUserService(applicationUserRepository: ApplicationUserRepository)
    : DefaultCRUDService<ApplicationUser, Long, ApplicationUserRepository>(applicationUserRepository) {
    abstract fun existsByUsername(username: String): Boolean
    abstract fun getByUsername(username: String): ApplicationUser?
    abstract fun register(user: ApplicationUser): Boolean
    abstract fun updateStatus(userId: Long, status: String): Boolean
    abstract fun updateAdminRights(userId: Long, admin: Boolean): Boolean
    abstract fun deleteUser(userId: Long): Boolean
}