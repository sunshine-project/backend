package com.example.sunshineserver.quest.presentation.dto;

import org.springframework.util.Assert;

public record QuestCompleteResponse(Long userId, Long user_quest_id, String result) {

    public QuestCompleteResponse {
        Assert.notNull(userId, "userId는 null이 될 수 없습니다");
        Assert.notNull(user_quest_id, "questId는 null이 될 수 없습니다");
    }
}
