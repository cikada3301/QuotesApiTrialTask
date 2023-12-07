package com.example.qutesapi.exception;

public class VoteExistException extends RuntimeException {
    public VoteExistException(String message) {
        super(message);
    }
}
