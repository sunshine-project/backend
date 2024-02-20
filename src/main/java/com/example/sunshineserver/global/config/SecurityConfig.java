package com.example.sunshineserver.global.config;

import com.example.sunshineserver.auth.domain.jwt.JwtTokenProvider;
import com.example.sunshineserver.auth.filter.JwtAuthenticationFilter;
import com.example.sunshineserver.global.domain.RequestUri;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    private final String[] allowedUrls = {"/api-docs/**", "/swagger-ui/**", "/v3/api-docs/**",
        "/swagger-resources/**", "/webjars/**", RequestUri.AUTH + "/**",
        RequestUri.USER
    };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .formLogin(AbstractHttpConfigurer::disable)
            .httpBasic(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth -> auth
	.requestMatchers(allowedUrls).permitAll()
	.anyRequest().authenticated())
            .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(
	SessionCreationPolicy.STATELESS))
            .addFilterBefore(jwtAuthenticationFilter(),
	UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter(jwtTokenProvider);
    }
}
