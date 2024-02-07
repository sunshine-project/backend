package com.example.sunshineserver.quest.domain;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public interface QuestTimer {

    LocalDate now();
}
