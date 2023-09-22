package com.example.crudapp.security.jwt;

import com.example.crudapp.model.User;
import com.example.crudapp.model.UserRoles;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory {

    public JwtUserFactory() {
    }

    public static JwtUser create(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAge(),
                mapToGrantedAuthorities(new ArrayList<>(user.getUserRoles()))
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(List<UserRoles> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getAuthority())
                ).collect(Collectors.toList());
    }
}