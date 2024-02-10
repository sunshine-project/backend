package com.example.sunshineserver.global.exception;

public class UserAlreadyExistedException extends RuntimeException{

    private static final String message = "User already existed";
    public UserAlreadyExistedException() {
        super(message);
    }
}
