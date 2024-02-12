package com.example.sunshineserver.auth.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "oauth.google")
public class GoogleProperties {

    private String clientId;
    private String clientSecret;
    private String redirectUri;
    private String tokenUri;
    private String resourceUri;

}