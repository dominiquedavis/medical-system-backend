package com.medicalsystem.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import com.medicalsystem.domain.id.LongIdComparableEntity
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.password.PasswordEncoder
import javax.persistence.Column
import javax.persistence.Entity

@Entity
data class ApplicationUser(

        @Column(name = "FULL_NAME")
        var fullName: String = "",

        @Column(name = "EMAIL", unique = true)
        var email: String = "",

        @Column(name = "USERNAME", unique = true)
        var username: String = "",

        @Column(name = "PASSWORD")
        var password: String = "",

        @Column(name = "STATUS")
        var status: String = "",

        @Column(name = "ADMIN")
        var admin: Boolean = false

) : LongIdComparableEntity() {

    @JsonIgnore
    fun getAuthorities(): List<GrantedAuthority> =
            if (admin) {
                listOf(SimpleGrantedAuthority("user"), SimpleGrantedAuthority("admin"))
            } else {
                listOf(SimpleGrantedAuthority("user"))
            }

    fun encryptPassword(passwordEncoder: PasswordEncoder) {
        password = passwordEncoder.encode(password)
    }
}