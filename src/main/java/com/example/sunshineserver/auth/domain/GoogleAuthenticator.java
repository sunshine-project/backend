package com.example.sunshineserver.auth.domain;

import com.example.sunshineserver.auth.presentation.dto.AccessTokenResponse;
import com.example.sunshineserver.auth.presentation.dto.UserResourceResponse;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class GoogleAuthenticator {

    @Value("${oauth.google.client-id}")
    private String clientId;
    @Value("${oauth.google.client-secret}")
    private String clientSecret;
    @Value("${oauth.google.redirect-uri}")
    private String redirectUri;
    @Value("${oauth.google.token-uri}")
    private String tokenUri;
    @Value("${oauth.google.token-info-uri}")
    private String tokenInfoUri;
    @Value("${oauth.google.resource-uri}")
    private String resourceUri;

    public AccessTokenResponse getAccessToken(String authCode) {
        WebClient webClient = WebClient.create();

        Mono<AccessTokenResponse> mono = webClient.post()
            .uri(tokenUri)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData("code", authCode)
	.with("client_id", clientId)
	.with("client_secret", clientSecret)
	.with("redirect_uri", redirectUri)
	.with("grant_type", "authorization_code"))
            .retrieve()
            .bodyToMono(JsonNode.class)
//            .doOnNext(jsonNode -> log.info("Received JsonNode: " + jsonNode)) // JsonNode 확인
            .map(jsonNode -> new AccessTokenResponse(jsonNode.get("access_token").asText(),
	jsonNode.get("id_token").asText()));

        return mono.block();
    }

    public UserResourceResponse getUserResource(String accessToken) {
        WebClient webClient = WebClient.create();

        Mono<UserResourceResponse> mono = webClient.get()
            .uri(resourceUri)
            .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
            .retrieve()
            .bodyToMono(JsonNode.class)
            .map(jsonNode -> new UserResourceResponse(jsonNode.get("email").asText()));

        return mono.block();
    }

    public String parseIdToken2Email(String idToken) {
        WebClient webClient = WebClient.create();

        Mono<String> mono = webClient.post()
            .uri(tokenInfoUri)
            .contentType(MediaType.APPLICATION_FORM_URLENCODED)
            .body(BodyInserters.fromFormData("id_token", idToken))
            .retrieve()
            .bodyToMono(JsonNode.class)
            .map(jsonNode -> jsonNode.get("email").asText());

        return mono.block();
    }
}
