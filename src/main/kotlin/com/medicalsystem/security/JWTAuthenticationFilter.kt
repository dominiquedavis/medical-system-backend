package com.medicalsystem.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.medicalsystem.domain.ApplicationUser
import com.medicalsystem.security.SecurityConstants.EXPIRATION_TIME
import com.medicalsystem.security.SecurityConstants.HEADER_STRING
import com.medicalsystem.security.SecurityConstants.LOGIN_URL
import com.medicalsystem.security.SecurityConstants.SECRET
import com.medicalsystem.security.SecurityConstants.SIGNATURE_ALGORITHM
import com.medicalsystem.security.SecurityConstants.TOKEN_PREFIX
import com.medicalsystem.service.ApplicationUserService
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.User
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import java.io.IOException
import java.util.*
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthenticationFilter(
        private val authManager: AuthenticationManager,
        private var userService: ApplicationUserService
) : UsernamePasswordAuthenticationFilter() {

    init {
        setFilterProcessesUrl(LOGIN_URL)
    }

    override fun attemptAuthentication(request: HttpServletRequest?, response: HttpServletResponse?): Authentication =
            try {
                val credentials: ApplicationUser =
                        ObjectMapper().readValue(request?.inputStream, ApplicationUser::class.java)

                if (!credentials.isAccepted()) {
                    throw IllegalAccessError("Konto użytkownika nie zostało jeszcze zaakceptowane.")
                }

                authManager.authenticate(
                        UsernamePasswordAuthenticationToken(
                                credentials.username,
                                credentials.password,
                                ArrayList()))

            } catch (e: IOException) {
                throw RuntimeException(e)
            }

    override fun successfulAuthentication(request: HttpServletRequest?, response: HttpServletResponse?,
                                          chain: FilterChain?, authResult: Authentication?) {
        val principal: User = authResult?.principal as User

        val token: String = Jwts.builder()
                .setSubject(principal.username)
                .claim("roles", principal.authorities.map { it.authority })
                .setExpiration(Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SIGNATURE_ALGORITHM, SECRET.toByteArray())
                .compact()

        response?.addHeader(HEADER_STRING, TOKEN_PREFIX + token)
    }

    private fun ApplicationUser.isAccepted(): Boolean {
        userService.findByUsername(this.username)?.let {
            return it.status == "Aktywny"
        }
        return false
    }
}