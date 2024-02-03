package com.example.sunshineserver.user;

import com.example.sunshineserver.user.domain.CharacterType;
import com.example.sunshineserver.user.domain.Gender;
import com.example.sunshineserver.user.domain.Stat;
import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserPort;
import com.example.sunshineserver.user.presentation.dto.UserCreateRequest;
import com.example.sunshineserver.user.service.UserService;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserPort userPort;


    @Test
    void 회원가입을_진행한다() {
        // given
        String name = "홍길동";
        Gender gender = Gender.MALE;
        LocalDate birthDay = LocalDate.of(1999, 3, 27);
        CharacterType characterType = CharacterType.A;
        Stat stat = Stat.of(1, 1, 1, 1);

        UserCreateRequest request = new UserCreateRequest(name, gender,
            birthDay, characterType, stat);

        // when
        userService.create(request);

        // then
        Assertions.assertThat(userPort.findAll()).hasSize(1);
    }

}
