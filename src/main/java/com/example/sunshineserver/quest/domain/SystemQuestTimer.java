package com.example.sunshineserver.quest.domain;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class SystemQuestTimer implements QuestTimer {

    @Override
    public LocalDate now() {
        return LocalDate.now();
    }
}
