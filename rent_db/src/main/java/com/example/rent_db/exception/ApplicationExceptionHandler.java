package com.example.rent_db.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(NotFoundConfigException.class)
    public ResponseEntity<?> exceptionProcessing(NotFoundConfigException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getNOT_FOUND_EXCEPTION_DESCRIPTION());
    }

    @ExceptionHandler(ApartmentException.class)
    public ResponseEntity<?> exceptionProcessing(ApartmentException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

}
