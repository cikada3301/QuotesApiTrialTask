package com.example.qutesapi.exception;

public class NotQuoteOwnerException extends RuntimeException {
    public NotQuoteOwnerException(String message) {
        super(message);
    }
}
