package com.medicalsystem.security

import com.medicalsystem.security.SecurityConstants.HEADER_STRING
import com.medicalsystem.security.SecurityConstants.SECRET
import com.medicalsystem.security.SecurityConstants.TOKEN_PREFIX
import io.jsonwebtoken.Jwts
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
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
            val user: String? = Jwts.parser()
                    .setSigningKey(SECRET.toByteArray())
                    .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                    .body
                    .subject
            user?.let {
                return UsernamePasswordAuthenticationToken(user, null, ArrayList())
            }
        }
        return null
    }
}