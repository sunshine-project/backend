package com.example.sunshineserver.global.exception;

import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({UserNotFoundedException.class, UserAlreadyExistedException.class,
        QuestNotExistedException.class, QuestTypeMismatchException.class, IOException.class, IllegalArgumentException.class, QuestAlreadyCompletedException.class, QuestAlreadyAssignedException.class})
    public ResponseEntity<Object> handle() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}