package com.example.sunshineserver.quest.domain;

import java.time.LocalDate;

public class SystemQuestTimer implements QuestTimer {

    @Override
    public LocalDate now() {
        return LocalDate.now();
    }
}
