package com.example.sunshineserver.user.presentation.dto;

import com.example.sunshineserver.user.domain.Stat;
import com.example.sunshineserver.user.domain.User;
import java.time.LocalDate;
import java.time.Period;

public record UserHomeResponse(Long id, String name, String characterType, Stat stat,
	               int experiencePoint, int level, int exclusiveRange,
	               int leftDay, long uncompletedQuestSize, boolean isAbleToEndGame) {

    static final int GAME_OVER_CRITERION = 70;

    public static UserHomeResponse from(User user) {

        int leftDay = GAME_OVER_CRITERION - Period.between(
	LocalDate.from(user.getCreatedAt()), LocalDate.now())
            .getDays();

        return new UserHomeResponse(user.getId(), user.getName(), user.getCharacterType().name(),
            user.getStat(),
            user.getExperiencePoint().getExperiencePoint(), user.getLevel().getLevel(),
            user.getLevel().getExclusiveRange(), leftDay,
            user.uncompletedQuestSize(), user.isAbleToEndGame());
    }
}
