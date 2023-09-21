package com.example.crudapp.model;

import org.springframework.security.core.GrantedAuthority;

public enum UserRoles implements GrantedAuthority {
    // Хозяин
    OWNER,

    // Жилец
    TENANT;

    @Override
    public String getAuthority() {
        return name();
    }
}
