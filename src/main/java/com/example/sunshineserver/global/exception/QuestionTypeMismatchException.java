package com.example.sunshineserver.global.exception;

public class QuestionTypeMismatchException extends RuntimeException{

    private static final String MESSAGE = "Quest type mismatched";
    public QuestionTypeMismatchException() {
        super(MESSAGE);
    }
}
