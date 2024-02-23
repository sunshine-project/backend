package com.example.sunshineserver.global.exception;

public class QuestAlreadyCompletedException extends RuntimeException {

    private static final String MESSAGE = "Quest already completed";

    public QuestAlreadyCompletedException() {
        super(MESSAGE);
    }
}
