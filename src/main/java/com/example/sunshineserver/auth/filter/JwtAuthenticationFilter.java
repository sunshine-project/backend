package com.example.sunshineserver.auth.filter;

import com.example.sunshineserver.auth.domain.jwt.JwtTokenProvider;
import com.example.sunshineserver.global.exception.TokenExpiredException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String BEARER_PREFIX = "Bearer ";

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain)
        throws ServletException, IOException {
        try {
            String token = resolveToken(request);

            if (token != null) {
	if (jwtTokenProvider.isTokenExpired(token)) {
	    throw new TokenExpiredException("Token expired");
	} else {
	    Authentication authentication = jwtTokenProvider.getAuthentication(token);
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	}
            }
            filterChain.doFilter(request, response);
        } catch (TokenExpiredException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token expired");
            return;
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
