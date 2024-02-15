package com.example.sunshineserver.auth.application;

import com.example.sunshineserver.auth.domain.GoogleAuthenticator;

import com.example.sunshineserver.auth.domain.jwt.JwtTokenProvider;
import com.example.sunshineserver.auth.domain.jwt.TokenInfo;
import com.example.sunshineserver.auth.domain.jwt.TokenInfoResponse;
import com.example.sunshineserver.auth.presentation.dto.AccessTokenResponse;
import com.example.sunshineserver.auth.presentation.dto.GoogleLoginResponse;
import com.example.sunshineserver.global.exception.UserNotFoundedException;
import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserPort;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class AuthService {

    private final GoogleAuthenticator googleAuthenticator;
    private final UserPort userPort;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public GoogleLoginResponse login(String idToken) {
        String email = googleAuthenticator.parseIdToken2Email(idToken);

        Optional<User> user = userPort.findByEmail(email);

        if (!user.isPresent()) {
            return new GoogleLoginResponse(null,
	null, email, false);
        }

        TokenInfo tokenInfo = jwtTokenProvider.createTokenInfo(email);

        return new GoogleLoginResponse(tokenInfo.getAccessToken(),
            tokenInfo.getRefreshToken(), email, true);
    }

    public AccessTokenResponse code(String authCode) {
        AccessTokenResponse accessTokenResponse = googleAuthenticator.getAccessToken(authCode);
        return accessTokenResponse;
    }

    public TokenInfo refreshToken(String refreshToken) {
        TokenInfoResponse refreshTokenInfo = jwtTokenProvider.parse(refreshToken);

        User user = userPort.findByEmail(refreshTokenInfo.email())
            .orElseThrow(() -> new UserNotFoundedException());

        if (!user.isRefreshTokenValid(refreshToken)) {
            throw new IllegalArgumentException("Refresh Token is not valid");
        }

        TokenInfo tokenInfo = jwtTokenProvider.createTokenInfo(user.getEmail());
        user.updateRefreshToken(tokenInfo.getRefreshToken());

        return tokenInfo;
    }
}
