package com.example.sunshineserver.user.domain;

import java.util.Arrays;

public enum Level {
    LV1(1, 0, 100),
    LV2(2, 100, 200),
    LV3(3, 200, 300),
    LV4(4, 300, 400),
    LV5(5, 400, 500);

    private int level;
    private int minExp;
    private int maxExp;

    Level(int level, int minExp, int maxExp) {
        this.level = level;
        this.minExp = minExp;
        this.maxExp = maxExp;
    }

    public static Level of(int level) {
        return Arrays.stream(Level.values())
            .filter(lv -> lv.level == level)
            .findFirst()
            .orElseThrow(IllegalArgumentException::new);
    }
}
