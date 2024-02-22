package com.example.sunshineserver.auth.presentation;

import com.example.sunshineserver.auth.application.AuthService;
import com.example.sunshineserver.auth.domain.jwt.TokenInfo;
import com.example.sunshineserver.auth.presentation.dto.AccessTokenResponse;
import com.example.sunshineserver.auth.presentation.dto.GoogleLoginResponse;
import com.example.sunshineserver.global.domain.RequestUri;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Tag(name = "Auth", description = "Auth API")
@RequestMapping(RequestUri.AUTH)
public class AuthController {

    private final AuthService authService;

    @GetMapping("/code/google")
    @Operation(summary = "Authorization Code 경로", description = "Authorization Code를 받을 수 있는 경로입니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "400", description = "회원 가입에 실패하였습니다."),
    })
    public ResponseEntity<AccessTokenResponse> code(@RequestParam("code") String authCode) {
        return new ResponseEntity<>(authService.code(authCode), HttpStatus.OK);
    }

    @PostMapping("/login/google")
    @Operation(summary = "구글 로그인", description = "구글 id_token을 통해 로그인을 진행합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "400", description = "회원 가입에 실패하였습니다."),
    })
    public ResponseEntity<GoogleLoginResponse> loginGoogle(
        @RequestParam("id_token") String idToken) {
        return new ResponseEntity<>(authService.login(idToken), HttpStatus.OK);
    }

    @PostMapping("/reissue")
    @Operation(summary = "토큰 재발급", description = "RefreshToken을 통해 AccessToken을 재발급합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "400", description = "회원 가입에 실패하였습니다."),
    })
    public ResponseEntity<TokenInfo> refreshToken(
        @RequestParam("refresh_token") String refreshToken) {
        return new ResponseEntity<>(authService.refreshToken(refreshToken), HttpStatus.OK);
    }
}
