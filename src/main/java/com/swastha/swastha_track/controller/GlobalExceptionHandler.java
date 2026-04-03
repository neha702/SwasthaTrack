package com.swastha.swastha_track.controller;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.swastha.swastha_track.utility.Constants.EMAIL_ALREADY_EXISTS;
import static com.swastha.swastha_track.utility.Constants.INVALID_CREDENTIALS;
import static com.swastha.swastha_track.utility.Constants.USER_NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleRuntime(Exception ex) {

        String message = ex.getMessage();

        if(ex instanceof MethodArgumentNotValidException) {
            return ResponseEntity.status(400).body(
                Map.of("error", message)
            );
        }

        if (USER_NOT_FOUND.equals(message)) {
            return ResponseEntity.status(404).body(
                Map.of("error", message)
            );
        }

        if (INVALID_CREDENTIALS.equals(message)) {
            return ResponseEntity.status(401).body(
                Map.of("error", INVALID_CREDENTIALS)
            );
        }

        if(EMAIL_ALREADY_EXISTS.equals(message)) {
            return ResponseEntity.status(409).body(
                Map.of("error", message)
            );
        }

        return ResponseEntity.status(400).body(
            Map.of("error", message)
        );
    }
}
