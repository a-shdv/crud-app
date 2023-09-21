package com.example.crudapp.controller;

import com.example.crudapp.exception.UserAlreadyExistsException;
import com.example.crudapp.exception.UserNotFoundException;
import com.example.crudapp.exception.UsersListIsEmptyException;
import com.example.crudapp.model.User;
import com.example.crudapp.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> register(@RequestBody User user) {
        try {
            userService.register(user);
            return ResponseEntity.ok("Успех!");
        } catch (UserAlreadyExistsException userAlreadyExistsException) {
            return ResponseEntity.badRequest().body(userAlreadyExistsException.toString());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        try {
            return ResponseEntity.ok(userService.getUsers());
        } catch (UsersListIsEmptyException usersListIsEmptyException) {
            return ResponseEntity.badRequest().body(usersListIsEmptyException.toString());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.getUserById(id));
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.badRequest().body(userNotFoundException.toString());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUserById(@PathVariable(name = "id") Long id,
                                            @RequestBody User reqUser) {
        try {
            return ResponseEntity.ok(userService.updateUserById(id, reqUser));
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.badRequest().body(userNotFoundException.toString());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(userService.deleteUserById(id));
        } catch (UserNotFoundException userNotFoundException) {
            return ResponseEntity.badRequest().body(userNotFoundException.toString());
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.toString());
        }
    }

}
