package com.medicalsystem.security

import com.medicalsystem.security.SecurityConstants.HEADER_STRING
import com.medicalsystem.security.SecurityConstants.SECRET
import com.medicalsystem.security.SecurityConstants.TOKEN_PREFIX
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTAuthorizationFilter(authManager: AuthenticationManager) : BasicAuthenticationFilter(authManager) {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        val header: String? = request.getHeader(HEADER_STRING)

        if (header != null && header.startsWith(TOKEN_PREFIX)) {
            val authentication: UsernamePasswordAuthenticationToken? = getAuthentication(request)
            SecurityContextHolder.getContext().authentication = authentication
        }

        chain.doFilter(request, response)
    }

    private fun getAuthentication(request: HttpServletRequest): UsernamePasswordAuthenticationToken? {
        val token: String? = request.getHeader(HEADER_STRING)
        token?.let {
            try {
                val subject: String? = getSubject(token)
                val roles: List<String> = getRoles(token)
                subject?.let {
                    return UsernamePasswordAuthenticationToken(subject, null, roles.map { SimpleGrantedAuthority(it) })
                }
            } catch (e: SignatureException) {
                logger.warn("Login denied: ${e.message}")
            }
        }
        return null
    }

    private fun getSubject(token: String): String? =
            getBody(token)
                    .subject

    private fun getRoles(token: String): List<String> =
            getBody(token)
                    .get("roles", ArrayList::class.java)
                    .mapNotNull { it as? String }

    private fun getBody(token: String): Claims =
            Jwts.parser()
                    .setSigningKey(SECRET.toByteArray())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .body
}