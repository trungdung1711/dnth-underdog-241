package com.dnth_underdog_241.online_fashion_shopping.exception;

import com.dnth_underdog_241.online_fashion_shopping.dto.response.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Global Exception Handler for all Controllers
 */
@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(UserAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException(UserAlreadyExistsException ex)
    {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException(UserNotFoundException ex)
    {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDto> handleUsernameNotFoundException(UsernameNotFoundException ex)
    {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<ErrorResponseDto> handleBadCredentialsException(BadCredentialsException ex)
    {
        return buildErrorResponse(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorResponseDto> handleValidationExceptions(MethodArgumentNotValidException ex)
    {
        List<String> errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String message = error.getDefaultMessage();
                    return fieldName + ": " + message;
                })
                .collect(Collectors.toList());
        String errorMessage = String.join(", ", errors);
        return buildErrorResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(ResourcesNotFound.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorResponseDto> handleResourcesNotFound(ResourcesNotFound ex)
    {
        return buildErrorResponse(ex.getMessage(), HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ResourceAlreadyExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<ErrorResponseDto> handleResourceAlreadyExistException(ResourceAlreadyExistException ex)
    {
        return buildErrorResponse(ex.getMessage(), HttpStatus.CONFLICT);
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex)
    {
        return buildErrorResponse(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Helper method to build a consistent error response.
     *
     * @param message the error message
     * @param status  the HTTP status code
     * @return a ResponseEntity with ErrorResponseDto
     */
    private ResponseEntity<ErrorResponseDto> buildErrorResponse(String message, HttpStatus status)
    {
        return ResponseEntity
                .status(status)
                .body(
                        ErrorResponseDto.builder()
                                .status(status.value())
                                .error(status.getReasonPhrase())
                                .message(message)
                                .path(null) // Path can be set dynamically if needed
                                .timestamp(LocalDateTime.now())
                                .build()
                );
    }
}