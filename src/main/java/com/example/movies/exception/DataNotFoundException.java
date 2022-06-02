package com.example.movies.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends RuntimeException{

    public DataNotFoundException(String message) {
        super(message);
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }

}
