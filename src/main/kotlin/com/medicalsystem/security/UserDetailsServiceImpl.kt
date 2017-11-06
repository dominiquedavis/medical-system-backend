package com.medicalsystem.security

import com.medicalsystem.model.ApplicationUser
import com.medicalsystem.service.ApplicationUserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl @Autowired constructor(val userService: ApplicationUserService) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: ApplicationUser? = userService.getByUsername(username)
        user?.let { return User(user.username, user.password, emptyList()) }
        throw UsernameNotFoundException(username)
    }
}