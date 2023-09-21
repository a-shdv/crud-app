package com.example.crudapp.exception;

public class UserListIsEmptyException extends Exception {
    public UserListIsEmptyException(String message) {
        super(message);
    }
}
