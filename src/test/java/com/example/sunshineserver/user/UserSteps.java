package com.example.sunshineserver.user;

import com.example.sunshineserver.user.domain.CharacterType;
import com.example.sunshineserver.user.domain.Gender;
import com.example.sunshineserver.user.domain.Stat;
import com.example.sunshineserver.user.presentation.dto.UserCreateRequest;
import java.time.LocalDate;

public class UserSteps {
    public static UserCreateRequest 유저_생성_요청() {
        String email = "gdscsunshine@gmail.com";
        String name = "홍길동";
        Gender gender = Gender.MALE;
        LocalDate birthDay = LocalDate.of(1999, 3, 27);
        CharacterType characterType = CharacterType.A;
        Stat stat = Stat.of(1, 1, 1, 1);

        UserCreateRequest request = new UserCreateRequest(email, name, gender,
            birthDay, characterType, stat);
        return request;
    }
}
