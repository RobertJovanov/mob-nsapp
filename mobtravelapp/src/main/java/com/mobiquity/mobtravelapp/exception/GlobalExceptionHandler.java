package com.mobiquity.mobtravelapp.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IncorrectFormatException.class, WeatherException.class})
    protected ResponseEntity<ApiError> incorrectFormatExceptionHandler(IncorrectFormatException ex) {
        return new ResponseEntity<ApiError>((new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage(),ex.getCause())), new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

}
