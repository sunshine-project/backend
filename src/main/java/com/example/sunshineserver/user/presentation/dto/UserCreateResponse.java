package com.example.sunshineserver.user.presentation.dto;

public record UserCreateResponse(Long userId) {

    public static UserCreateResponse from(Long userId) {
        return new UserCreateResponse(userId);
    }

}
