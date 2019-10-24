package com.mobiquity.mobtravelapp.exception;

public class IncorrectFormatException extends RuntimeException{

    public IncorrectFormatException(String message) {
        super(message);
    }
    public IncorrectFormatException(String message, Throwable cause) {
        super(message, cause);
    }

}
