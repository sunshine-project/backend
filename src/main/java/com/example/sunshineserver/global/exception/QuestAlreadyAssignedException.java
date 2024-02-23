package com.example.sunshineserver.global.exception;

public class QuestAlreadyAssignedException extends RuntimeException {

    private static final String MESSAGE = "Quest already assigned";

    public QuestAlreadyAssignedException() {
        super(MESSAGE);
    }
}
