package com.finishherlezlah.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RateLimitException.class)
    public ResponseEntity<Map<String, Object>> handleRoastException(RateLimitException ex) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("error code", ex.getErrorCode());
        body.put("error description", ex.getErrorDescription());
        body.put("message", ex.getMessage()); // pulled from RuntimeException's message

        return new ResponseEntity<>(body, HttpStatus.TOO_MANY_REQUESTS);
    }
}

