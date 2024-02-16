package com.example.sunshineserver.user.presentation.dto;

public record UserCreateResponse(String email) {

    public static UserCreateResponse from(String email) {
        return new UserCreateResponse(email);
    }

}
