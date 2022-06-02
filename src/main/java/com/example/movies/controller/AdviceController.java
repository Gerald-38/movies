package com.example.movies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.movies.exception.DataNotFoundException;

@ControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = { DataNotFoundException.class })
    protected ResponseEntity<Object> handleSubjectRegistrationException(DataNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

}
