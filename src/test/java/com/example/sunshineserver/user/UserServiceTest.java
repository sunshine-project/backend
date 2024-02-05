package com.example.sunshineserver.user;

import com.example.sunshineserver.user.domain.repository.UserPort;
import com.example.sunshineserver.user.presentation.dto.UserCreateRequest;
import com.example.sunshineserver.user.application.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPort userPort;

    @BeforeEach
    void setUp() {
        userPort.deleteAll();
    }

    @Test
    void 회원가입을_진행한다() {
        // given
        UserCreateRequest request = UserSteps.유저_생성_요청();

        // when
        userService.create(request);

        // then
        Assertions.assertThat(userPort.findAll()).hasSize(1);
    }
}
