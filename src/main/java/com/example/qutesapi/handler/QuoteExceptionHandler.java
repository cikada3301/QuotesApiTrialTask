package com.example.qutesapi.handler;

import com.example.qutesapi.exception.QuoteNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class QuoteExceptionHandler {

    @ExceptionHandler(QuoteNotFoundException.class)
    public ResponseEntity<String> handleTaskNotFoundException(QuoteNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
