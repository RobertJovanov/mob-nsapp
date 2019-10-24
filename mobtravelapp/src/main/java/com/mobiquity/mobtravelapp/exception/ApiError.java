package com.mobiquity.mobtravelapp.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiError {
    private HttpStatus status;

    private String error;
    private Throwable errorMsg;

}
