package com.example.crudapp.controller;

import com.example.crudapp.dto.UserLoginDto;
import com.example.crudapp.exception.UserAlreadyExistsException;
import com.example.crudapp.exception.UserNotFoundException;
import com.example.crudapp.exception.UsersListIsEmptyException;
import com.example.crudapp.model.User;
import com.example.crudapp.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            userService.register(user);
            return ResponseEntity.ok("Успех!");
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            return ResponseEntity.badRequest().body(userAlreadyExistsException.toString());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserLoginDto reqUser) {
        try {
            Map<Object, Object> response = userService.login(reqUser);
            return ResponseEntity.ok(response);
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.badRequest().body(userNotFoundException.toString());
        }
    }


    @GetMapping
    public ResponseEntity<?> getUsers() {
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (UsersListIsEmptyException usersListIsEmptyException) {
            return ResponseEntity.badRequest().body(usersListIsEmptyException.toString());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.badRequest().body(userNotFoundException.toString());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable(name = "id") Long id,
                                            @RequestBody User reqUser) {
        try {
            return ResponseEntity.ok(userService.updateUserById(id, reqUser));
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.badRequest().body(userNotFoundException.toString());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUserById(id));
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.badRequest().body(userNotFoundException.toString());
        }
    }

}
