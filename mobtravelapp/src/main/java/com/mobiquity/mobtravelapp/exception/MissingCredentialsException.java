package com.mobiquity.mobtravelapp.exception;

public class MissingCredentialsException extends NullPointerException{

    public MissingCredentialsException(String message){
        super(message);
    }
}
