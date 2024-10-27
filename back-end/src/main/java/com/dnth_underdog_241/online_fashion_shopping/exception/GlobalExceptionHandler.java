package com.dnth_underdog_241.online_fashion_shopping.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;


@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException)
    {
        Map<String, String> response = new HashMap<String, String>();
        response.put("error", userAlreadyExistsException.getMessage());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(response);
    }
}
