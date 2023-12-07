package com.example.qutesapi.exception;

public class UserExistException extends RuntimeException {
    public UserExistException(String message) {
        super(message);
    }
}
