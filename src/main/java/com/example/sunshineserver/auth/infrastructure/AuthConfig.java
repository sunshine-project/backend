package com.example.sunshineserver.auth.infrastructure;

import com.example.sunshineserver.auth.domain.GoogleAuthenticator;
import com.example.sunshineserver.auth.domain.GoogleProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfig {

    @Bean
    public GoogleAuthenticator googleAuthenticator(GoogleProperties googleProperties) {
        return new GoogleAuthenticator(googleProperties);
    }
}
