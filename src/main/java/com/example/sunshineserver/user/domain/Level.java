package com.example.sunshineserver.user.domain;

import java.util.Arrays;

public enum Level {
    LV1(1, 0, 1000),
    LV2(2, 1000, 2500),
    LV3(3, 2500, 4000),
    LV4(4, 4000, 6000),
    LV5(5, 6000, 10000);

    private final int level;
    private final int inclusiveRange;
    private final int exclusiveRange;

    Level(int level, int inclusiveRange, int exclusiveRange) {
        this.level = level;
        this.inclusiveRange = inclusiveRange;
        this.exclusiveRange = exclusiveRange;
    }

    public int getLevel() {
        return level;
    }

    public int getExclusiveRange() {
        return exclusiveRange;
    }

    public static Level from(Integer experience) {
        return Arrays.stream(Level.values())
            .filter(
	level -> level.inclusiveRange <= experience && level.exclusiveRange > experience)
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 레벨이 없습니다."));
    }
}
