package com.example.crudapp.exception;

public class HouseAlreadyExistsException extends Exception {
    public HouseAlreadyExistsException(String message) {
        super(message);
    }
}
