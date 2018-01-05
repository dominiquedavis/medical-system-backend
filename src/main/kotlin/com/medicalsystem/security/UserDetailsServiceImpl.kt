package com.medicalsystem.security

import com.medicalsystem.domain.ApplicationUser
import com.medicalsystem.service.ApplicationUserService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(private val userService: ApplicationUserService) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user: ApplicationUser? = userService.findByUsername(username)
        user?.let { return User(user.username, user.password, user.getAuthorities()) }
        throw UsernameNotFoundException(username)
    }
}