package com.medicalsystem.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import javax.persistence.*

@Entity
@Table(name = "USERS")
data class ApplicationUser(

        @Id @GeneratedValue
        @Column(name = "ID")
        var id: Long = 0,

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
) {
    fun getAuthorities(): List<GrantedAuthority> {
        val authorities: MutableList<SimpleGrantedAuthority> = mutableListOf(SimpleGrantedAuthority("user"))
        if (admin) {
            authorities.add(SimpleGrantedAuthority("admin"))
        }
        return authorities
    }
}