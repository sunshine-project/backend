package com.example.sunshineserver.auth.presentation.dto;

import jakarta.validation.constraints.NotNull;

public record GoogleLoginRequest(@NotNull String authCode) {

}
