package com.example.sunshineserver.global.exception;

public class QuestNotExistedException extends RuntimeException{

    private static final String MESSAGE = "Quest not existed";
    public QuestNotExistedException() {
        super(MESSAGE);
    }
}
