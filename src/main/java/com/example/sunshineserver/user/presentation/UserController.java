package com.example.sunshineserver.user.presentation;

import com.example.sunshineserver.global.domain.RequestURI;
import com.example.sunshineserver.user.presentation.dto.UserCreateRequest;
import com.example.sunshineserver.user.application.UserService;
import com.example.sunshineserver.user.presentation.dto.UserHomeResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(RequestURI.USER)
@Tag(name = "User", description = "User API")
public class UserController {

    private final UserService userService;

    @PostMapping
    @Operation(summary = "유저 생성", description = "유저를 생성합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "회원 가입에 실패하였습니다."),
    })
    public void create(@RequestBody UserCreateRequest request) {
        userService.create(request);
    }

    @GetMapping
    @Operation(summary = "홈 화면 조회", description = "홈 화면을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "유저를 찾을 수 없습니다."),
    })
    public UserHomeResponse findHome(Long userId) {
        return userService.findHome(userId);
    }
}
