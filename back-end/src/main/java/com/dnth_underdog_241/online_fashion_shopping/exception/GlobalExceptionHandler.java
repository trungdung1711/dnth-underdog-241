package com.dnth_underdog_241.online_fashion_shopping.exception;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.HashMap;
import java.util.Map;


/**
 * Global Exception Handler for all Controllers
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    @Value("${com.dnth_underdog_241.online_fashion_shopping.server.name}")
    String serverName;


    String name = "GlobalExceptionHandler";


    /**
     * Handle the UserAlreadyExistsException.
     *
     * @param  UserAlreadyExistsException the exception.
     * @return ResponseEntity with status CONFLICT.
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleUserAlreadyExistsException(UserAlreadyExistsException userAlreadyExistsException)
    {
        Map<String, String> response = new HashMap<>();
        response.put(serverName + name, userAlreadyExistsException.getMessage());
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(response);
    }


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCustomerIdNotFoundException(UserNotFoundException customerNotFoundException)
    {
        Map<String, String> response = new HashMap<>();
        response.put(serverName + name, customerNotFoundException.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException)
    {
        Map<String, String> response = new HashMap<>();
        response.put(serverName + name, usernameNotFoundException.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }


    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleBadCredentialsException(BadCredentialsException badCredentialsException)
    {
        Map<String, String> response = new HashMap<>();
        response.put(serverName + name, badCredentialsException.getMessage());
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(response);
    }


    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoResourceFoundException(NoResourceFoundException noResourceFoundException)
    {
        Map<String, String> response = new HashMap<>();
        response.put(serverName + name, noResourceFoundException.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(response);
    }


    /**
     * Catch the exception thrown by the
     * annotation PreAuthorize in methods
     * @param authorizationDeniedException thrown
     * when authenticated user can't access
     * the resources which required higher Role
     * @return ResponseEntity with 403 Forbidden
     */
    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAuthorizationDeniedException(AuthorizationDeniedException authorizationDeniedException)
    {
        Map<String, String> response = new HashMap<>();
        response.put(serverName + name, authorizationDeniedException.getMessage());
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(response);
    }


    /**
     *
     * @param exception Exception which is caught all
     * @return ResponseEntity with INTERNAL_SERVER_ERROR code
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleException(Exception exception)
    {
        Map<String, String> response = new HashMap<>();
        response.put(serverName + "Global_Exception_handler_catch_all", exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }
}
