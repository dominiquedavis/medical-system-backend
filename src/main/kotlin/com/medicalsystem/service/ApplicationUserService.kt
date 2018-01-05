package com.medicalsystem.service

import com.medicalsystem.domain.ApplicationUser
import com.medicalsystem.exception.ADMIN_RIGHTS_UPDATED_FOR_USER
import com.medicalsystem.exception.STATUS_UPDATED_FOR_USER
import com.medicalsystem.exception.USER_EXISTS_WITH_USERNAME
import com.medicalsystem.exception.USER_NOT_FOUND_WITH_ID
import com.medicalsystem.repository.ApplicationUserRepository
import com.medicalsystem.util.logger
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class ApplicationUserService(
        private val userRepository: ApplicationUserRepository,
        private val passwordEncoder: BCryptPasswordEncoder
) : CRUDService<ApplicationUser, Long>(userRepository) {

    fun exists(username: String): Boolean =
            findByUsername(username) != null

    fun findByUsername(username: String): ApplicationUser? =
            userRepository.findByUsername(username)

    fun updateStatus(userId: Long, status: String): Boolean {
        val user: ApplicationUser? = findByID(userId)

        if (user == null) {
            logger().error(USER_NOT_FOUND_WITH_ID + userId)
            return false
        }

        user.status = status
        save(user)

        logger().info(STATUS_UPDATED_FOR_USER + "${user.username}: $status")

        return true
    }

    fun updateAdminRights(userId: Long, admin: Boolean): Boolean {
        val user: ApplicationUser? = findByID(userId)

        if (user == null) {
            logger().error(USER_NOT_FOUND_WITH_ID + userId)
            return false
        }

        user.admin = admin
        save(user)

        logger().info(ADMIN_RIGHTS_UPDATED_FOR_USER + "${user.username}: $admin")

        return true
    }

    fun deleteUser(userId: Long): Boolean =
            if (exists(userId)) {
                deleteByID(userId)
                true
            } else {
                logger().error(USER_NOT_FOUND_WITH_ID + userId)
                false
            }

    fun register(user: ApplicationUser): Boolean =
            if (exists(user.username)) {
                logger().error(USER_EXISTS_WITH_USERNAME + user.username)
                false
            } else {
                user.encryptPassword(passwordEncoder)
                save(user)
                true
            }
}