package com.nikola.LoansApi.exceptions;

import com.nikola.LoansApi.models.dto.ResponseErrorObj;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseErrorObj> handleAccessDeniedException(
            AccessDeniedException ex) {
        return new ResponseEntity<>(new ResponseErrorObj(ex.getMessage()), FORBIDDEN);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ResponseErrorObj> handleNotFoundException(
            NotFoundException ex) {
        return new ResponseEntity<>(new ResponseErrorObj(ex.getMessage()), NOT_FOUND);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ResponseErrorObj> handleBadRequestException(
            BadRequestException ex) {
        return new ResponseEntity<>(new ResponseErrorObj(ex.getMessage()), BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseErrorObj> handleRuntimeException(
            RuntimeException ex) {
        ex.printStackTrace();
        return new ResponseEntity<>(new ResponseErrorObj("Something went wrong"), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, BAD_REQUEST);
    }
}
