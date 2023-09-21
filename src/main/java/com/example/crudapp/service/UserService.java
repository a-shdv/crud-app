package com.example.crudapp.service;

import com.example.crudapp.dto.UserDto;
import com.example.crudapp.exception.UserAlreadyExistsException;
import com.example.crudapp.exception.UserNotFoundException;
import com.example.crudapp.exception.UsersListIsEmptyException;
import com.example.crudapp.model.User;

import java.util.List;

public interface UserService {
    void register(User user) throws UserAlreadyExistsException;

    void login();

    List<UserDto> getUsers() throws UsersListIsEmptyException;

    UserDto getUserById(Long id) throws UserNotFoundException;

    UserDto updateUserById(Long id, User reqUser) throws UserNotFoundException;

    Long deleteUserById(Long id) throws UserNotFoundException;

}