package com.example.crudapp.security;

import com.example.crudapp.exception.UserNotFoundException;
import com.example.crudapp.model.User;
import com.example.crudapp.security.jwt.JwtUserFactory;
import com.example.crudapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    private final UserService userService;

    @Autowired
    public JwtUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userService.findUserByUsername(username);
        } catch (UserNotFoundException e) {
            System.out.printf("Пользователь не найден! %s", e);
        }

        return JwtUserFactory.create(user);
    }
}
