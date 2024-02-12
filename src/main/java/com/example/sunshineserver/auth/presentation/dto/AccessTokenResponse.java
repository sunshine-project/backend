package com.example.sunshineserver.auth.presentation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AccessTokenResponse(@JsonProperty("access_token") String accessToken, @JsonProperty("refresh_token") String refreshToken
) {

}
