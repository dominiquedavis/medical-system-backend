package com.medicalsystem.model

import com.fasterxml.jackson.annotation.JsonIgnore
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
    @JsonIgnore
    fun getAuthorities(): List<GrantedAuthority> =
            if (admin) {
                listOf(SimpleGrantedAuthority("user"), SimpleGrantedAuthority("admin"))
            } else {
                listOf(SimpleGrantedAuthority("user"))
            }
}