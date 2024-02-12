package com.example.sunshineserver.auth.domain;

import com.example.sunshineserver.auth.presentation.dto.AccessTokenResponse;
import com.example.sunshineserver.auth.presentation.dto.UserResourceResponse;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public class GoogleAuthenticator {

    private final GoogleProperties googleProperties;

    public AccessTokenResponse getAccessToken(String authCode) {
        WebClient webClient = WebClient.create();

        Mono<AccessTokenResponse> mono = webClient.post()
            .uri(googleProperties.getTokenUri())
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData("code", authCode)
	.with("client_id", googleProperties.getClientId())
	.with("client_secret", googleProperties.getClientSecret())
	.with("redirect_uri", googleProperties.getRedirectUri())
	.with("grant_type", "authorization_code"))
            .retrieve()
            .bodyToMono(JsonNode.class)
            .map(jsonNode -> new AccessTokenResponse(jsonNode.get("access_token").asText(),
	jsonNode.get("refresh_token").asText()));

        return mono.block();
    }

    public UserResourceResponse getUserResource(String accessToken) {
        WebClient webClient = WebClient.create();

        Mono<UserResourceResponse> mono = webClient.get()
            .uri("oauth2." + googleProperties.getResourceUri() + ".resource-uri")
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
            .retrieve()
            .bodyToMono(JsonNode.class)
            .map(jsonNode -> new UserResourceResponse(jsonNode.get("email").asText()));

        return mono.block();
    }
}
