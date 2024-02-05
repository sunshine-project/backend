package com.example.sunshineserver.quest.domain;

import com.example.sunshineserver.user.domain.StatType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Embeddable
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StatInfo {

    @Enumerated(EnumType.STRING) // StatType이 Enum인 경우, Enum 타입을 명시적으로 지정
    private StatType statType;
    private int statValue;

    public static StatInfo of(StatType statType, int value) {
        return new StatInfo(statType, value);
    }
}
