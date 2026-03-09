package com.addressbook.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AddressBookExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleDuplicateException(IllegalArgumentException ex) {

        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}