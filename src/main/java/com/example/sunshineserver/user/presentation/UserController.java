package com.example.sunshineserver.user.presentation;

import com.example.sunshineserver.global.domain.RequestURI;
import com.example.sunshineserver.user.presentation.dto.UserCreateRequest;
import com.example.sunshineserver.user.application.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(RequestURI.USER)
public class UserController {

    private final UserService userService;

    @PostMapping
    public void create(@RequestBody UserCreateRequest request) {
        userService.create(request);
    }
}
