package com.example.crudapp.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    // Хозяин
    ROLE_OWNER,

    // Жилец
    ROLE_TENANT;

    @Override
    public String getAuthority() {
        return name();
    }
}
