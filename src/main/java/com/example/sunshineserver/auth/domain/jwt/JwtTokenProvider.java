package com.example.sunshineserver.auth.domain.jwt;

import com.example.sunshineserver.auth.domain.CustomUserDetailsService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.ArrayList;
import java.util.Date;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private static final String BEARER_MESSAGE = "Bearer";
    private final CustomUserDetailsService customUserDetailsService;

    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.access.expiration}")
    private long accessTokenExpiration;
    @Value("${jwt.access.header}")
    private String accessTokenHeader;
    @Value("${jwt.refresh.expiration}")
    private long refreshTokenExpiration;
    @Value("${jwt.refresh.header}")
    private String refreshTokenHeader;
    @Value("${jwt.issuer}")
    private String issuer;

    public TokenInfo createTokenInfo(final String subject) {
        long now = new Date().getTime();
        String accessToken = createToken(subject, accessTokenExpiration, now);
        String refreshToken = createToken(subject, refreshTokenExpiration, now);

        return TokenInfo.builder()
            .grantType(BEARER_MESSAGE)
            .accessToken(accessToken)
            .refreshToken(refreshToken)
            .build();
    }

    private String createToken(String subject, long expires, long now) {
        return Jwts.builder()
            .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
            .setSubject(subject)
            .setExpiration(new Date(now + expires * 1000L))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .setIssuer(issuer)
            .compact();
    }

    public TokenInfoResponse parse(String token) {
        try {
            String email = getAllClaimsFromToken(token).getSubject();
            return new TokenInfoResponse(email);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid Token");
        } catch (ExpiredJwtException e) {
            throw new IllegalArgumentException("Expired Token");
        }
    }

    public boolean isTokenExpired(String token) {
        try {
            getExpirationDateFromToken(token);
            return false;
        } catch (ExpiredJwtException e) {
            return true;
        }
    }

    private Date getExpirationDateFromToken(String token) {
        return getAllClaimsFromToken(token).getExpiration();
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token)
            .getBody();
    }

    public Authentication getAuthentication(String token) {
        Claims claims = getAllClaimsFromToken(token);

        String email = claims.getSubject();

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(
            email);
        return new UsernamePasswordAuthenticationToken(userDetails, "", new ArrayList<>());
    }
}
