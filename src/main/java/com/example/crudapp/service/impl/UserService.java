package com.example.crudapp.service.impl;

import com.example.crudapp.dto.UserDto;
import com.example.crudapp.dto.UserLoginDto;
import com.example.crudapp.exception.UserAlreadyExistsException;
import com.example.crudapp.exception.UserNotFoundException;
import com.example.crudapp.exception.UsersListIsEmptyException;
import com.example.crudapp.model.User;
import com.example.crudapp.model.UserRoles;
import com.example.crudapp.repository.UserRepo;
import com.example.crudapp.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements com.example.crudapp.service.UserService {
    private final UserRepo userRepo;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepo userRepo, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, BCryptPasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void register(User user) throws UserAlreadyExistsException {
        if (findUserByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("Пользователь с именем " + user.getUsername()
                    + " уже существует!");
        }
        user.setUserRoles(Collections.singletonList(UserRoles.TENANT));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(user);
    }

    @Override
    public Map<Object, Object> login(UserLoginDto dto) throws UserNotFoundException {
        User user = userRepo.findUserByUsername(dto.getUsername());
        Map<Object, Object> response = new HashMap<>();
        if (user == null) {
            throw new UserNotFoundException("Пользователь с именем " + user.getUsername() + " не найден!");
        }

        String token = jwtTokenProvider.createToken(user.getUsername(), user.getUserRoles());

        response.put("username", user.getUsername());
        response.put("token", token);

        return response;
    }

    @Override
    public List<UserDto> getUsers() throws UsersListIsEmptyException {;
        List<User> users = userRepo.findAll();

        if (users.isEmpty()) {
            throw new UsersListIsEmptyException("Список пользователей пуст!");
        }

        return UserDto.toUsersDto(users);
    }

    @Override
    public UserDto getUserById(Long id) throws UserNotFoundException {
        User user = userRepo.findById(id).get();
        if (user.getId() == null) {
            throw new UserNotFoundException("Пользователь не найден!");
        }
        return UserDto.toUserDto(user);
    }

    @Override
    public UserDto updateUserById(Long id, User reqUser) throws UserNotFoundException {
        User dbUser = userRepo.findById(id).get();
        if (dbUser.getId() == null) {
            throw new UserNotFoundException("Пользователь не найден!");
        }
        dbUser.setUsername(reqUser.getUsername());
        dbUser.setPassword(reqUser.getPassword());
        dbUser.setAge(reqUser.getAge());
        userRepo.save(dbUser);
        return UserDto.toUserDto(dbUser);
    }

    @Override
    public Long deleteUserById(Long id) throws UserNotFoundException {
        User user = userRepo.findById(id).get();
        if (user.getId() == null) {
            throw new UserNotFoundException("Пользователь не найден!");
        }
        userRepo.deleteById(id);
        return id;
    }

    @Override
    public User findUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findUserByUsername(username);
    }
}
