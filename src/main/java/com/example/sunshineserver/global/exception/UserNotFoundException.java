package com.example.sunshineserver.global.exception;

public class UserNotFoundException extends RuntimeException {

    private static final String message = "User Not Found";

    public UserNotFoundException() {
        super(message);
    }

}
