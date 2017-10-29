package com.medicalsystem.util;

import com.medicalsystem.model.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class RoleUtils {

    private RoleUtils() {}

    public static List<String> getRoles(Collection<GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
    }

    public static Collection<GrantedAuthority> getAuthorities(List<String> roles) {
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public static Collection<GrantedAuthority> getAuthorities(ApplicationUser user) {
        List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add(new SimpleGrantedAuthority("user"));

        if (user.isAdmin()) {
            authorities.add(new SimpleGrantedAuthority("admin"));
        }

        return authorities;
    }
}
