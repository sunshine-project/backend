package com.example.sunshineserver.global.exception;

public class UserNotFoundedException extends RuntimeException {

    private static final String MESSAGE = "User Not Found";

    public UserNotFoundedException() {
        super(MESSAGE);
    }

}
