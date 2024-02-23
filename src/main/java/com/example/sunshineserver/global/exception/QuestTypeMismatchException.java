package com.example.sunshineserver.global.exception;

public class QuestTypeMismatchException extends RuntimeException{

    private static final String MESSAGE = "Quest type mismatched";
    public QuestTypeMismatchException() {
        super(MESSAGE);
    }
}
