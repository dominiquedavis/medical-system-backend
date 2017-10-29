package com.medicalsystem.util;

import com.medicalsystem.model.ApplicationUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public final class RoleUtils {

    private RoleUtils() {}

    public static String[] getRoles(Collection<GrantedAuthority> authorities) {
        return authorities.stream().map(GrantedAuthority::getAuthority).toArray(String[]::new);
    }

    public static Collection<GrantedAuthority> getAuthorities(String[] roles) {
        return Arrays.stream(roles).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
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
