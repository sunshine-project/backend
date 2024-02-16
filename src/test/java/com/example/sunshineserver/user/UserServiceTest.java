package com.example.sunshineserver.user;

import com.example.sunshineserver.auth.domain.CustomUserDetails;
import com.example.sunshineserver.user.domain.repository.UserPort;
import com.example.sunshineserver.user.presentation.dto.UserCreateRequest;
import com.example.sunshineserver.user.application.UserService;
import com.example.sunshineserver.user.presentation.dto.UserCreateResponse;
import com.example.sunshineserver.user.presentation.dto.UserHomeResponse;
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

    @Test
    void 홈_화면을_조회한다() {
        // given
        UserCreateRequest request = UserSteps.유저_생성_요청();
        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());

        // when
        UserHomeResponse response = userService.home(
            new CustomUserDetails(userPort.findByEmail(userCreateResponse.email()).get()));

        // then
        Assertions.assertThat(response).isNotNull();
        Assertions.assertThat(response.name()).isEqualTo(request.name());
        Assertions.assertThat(response.characterType()).isEqualTo(request.characterType().name());
        Assertions.assertThat(response.stat()).isEqualTo(request.stat());
        Assertions.assertThat(response.experiencePoint()).isEqualTo(0);
        Assertions.assertThat(response.level()).isEqualTo(1);
        Assertions.assertThat(response.exclusiveRange()).isEqualTo(1000);
        Assertions.assertThat(response.leftDay()).isEqualTo(70);
        Assertions.assertThat(response.isAbleToEndGame()).isFalse();
    }
}
