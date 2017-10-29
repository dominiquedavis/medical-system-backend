package com.medicalsystem.security;

import com.medicalsystem.util.RoleUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import lombok.extern.java.Log;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.medicalsystem.security.SecurityConstants.*;

@Log
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String header = request.getHeader(HEADER_STRING);

        if (header == null || !header.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(request, response);
            return;
        }

        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token == null) {
            return null;
        }

        String subject = null;
        String[] roles = new String[0];
        try {
            subject = getSubject(token);
            roles = getRoles(token);
        } catch (SignatureException e) {
            log.warning("Login denied: " + e.getMessage());
        }

        return (subject == null) ? null : new UsernamePasswordAuthenticationToken(subject, null, RoleUtils.getAuthorities(roles));
    }

    private String getSubject(String token) throws SignatureException {
        return Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .getSubject();
    }

    private String[] getRoles(String token) throws SignatureException {
        List list = Jwts.parser()
                .setSigningKey(SECRET.getBytes())
                .parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
                .getBody()
                .get("roles", ArrayList.class);

        String[] roles = new String[list.size()];
        int i = 0;
        for (Object o : list) {
            if (!(o instanceof String)) {
                throw new SignatureException("'roles' element is not a string");
            }
            roles[i++] = (String) o;
        }

        return roles;
    }
}
