package com.example.sunshineserver.user;

import com.example.sunshineserver.quest.domain.StatInfo;
import com.example.sunshineserver.user.domain.CharacterType;
import com.example.sunshineserver.user.domain.ExperiencePoint;
import com.example.sunshineserver.user.domain.Gender;
import com.example.sunshineserver.user.domain.Stat;
import com.example.sunshineserver.user.domain.StatType;
import com.example.sunshineserver.user.domain.User;
import java.time.LocalDate;
import java.time.Period;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    void 경험치와_스탯을_증가한다() {
        // given
        String email = "gdscsunshine@gmail.com";
        String name = "홍길동";
        Gender gender = Gender.MALE;
        LocalDate birthDay = LocalDate.of(1999, 3, 27);
        CharacterType characterType = CharacterType.A;
        Stat stat = Stat.of(1, 1, 1, 1);

        User user = User.of(email, name, gender, birthDay, characterType, stat);

        // when
        user.addExperiencePoint(ExperiencePoint.from(300));
        user.addStat(StatInfo.of(StatType.STR, 1));

        // then
        Assertions.assertThat(user.getExperiencePoint().get())
            .isEqualTo(300);
        Assertions.assertThat(user.getStat().getStr())
            .isEqualTo(2);
    }

    @Test
    void 남은_일수를_계산한다() {
        // given
        int GAME_OVER_CRITERION = 70;
        LocalDate createdAt = LocalDate.of(2024, 2, 19);
        int leftDay = GAME_OVER_CRITERION - Period.between(
	createdAt, LocalDate.now())
            .getDays();

        // then
        Assertions.assertThat(leftDay)
            .isEqualTo(66);
    }
}
