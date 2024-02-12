package com.example.sunshineserver.auth.presentation.dto;

public record GoogleLoginResponse(String accessToken, String refreshToken, String email,
		  boolean isRegistered) {

}
