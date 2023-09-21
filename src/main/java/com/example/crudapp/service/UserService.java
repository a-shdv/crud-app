package com.example.crudapp.service;

import com.example.crudapp.dto.UserDto;
import com.example.crudapp.exception.UserAlreadyExistsException;
import com.example.crudapp.exception.UserListIsEmptyException;
import com.example.crudapp.exception.UserNotFoundException;
import com.example.crudapp.model.User;
import com.example.crudapp.repository.UserRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepo userRepo;

    public UserService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public User register(User user) throws UserAlreadyExistsException {
        if (userRepo.findByUsername(user.getUsername()) != null) {
            throw new UserAlreadyExistsException("Пользователь с именем " + user.getUsername()
                    + " уже существует!");
        }
        return userRepo.save(user);
    }

    public List<UserDto> getUsers() throws UserListIsEmptyException {
        List<User> users = this.userRepo.findAll();
        if (users.isEmpty()) {
            throw new UserListIsEmptyException("Список пользователей пуст!");
        }
        return UserDto.toUsersDto(users);
    }

    public UserDto getUserById(Long id) throws UserNotFoundException {
        User user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден!");
        }
        return UserDto.toUserDto(user);
    }

    public UserDto updateUserById(Long id, User reqUser) throws UserNotFoundException {
        User dbUser = userRepo.findById(id).get();
        if (dbUser == null) {
            throw new UserNotFoundException("Пользователь не найден!");
        }
        dbUser.setUsername(reqUser.getUsername());
        dbUser.setPassword(reqUser.getPassword());
        dbUser.setAge(reqUser.getAge());
        userRepo.save(dbUser);
        return UserDto.toUserDto(dbUser);
    }

    public Long deleteUserById(Long id) throws UserNotFoundException {
        User user = userRepo.findById(id).get();
        if (user == null) {
            throw new UserNotFoundException("Пользователь не найден!");
        }
        userRepo.deleteById(id);
        return id;
    }
}
