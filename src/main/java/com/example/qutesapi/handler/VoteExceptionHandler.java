package com.example.qutesapi.handler;

import com.example.qutesapi.exception.VoteExistException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class VoteExceptionHandler {

    @ExceptionHandler(VoteExistException.class)
    public ResponseEntity<String> handleVoteExistException(VoteExistException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getMessage());
    }
}
