package com.example.sunshineserver.quest.presentation.dto;

import org.springframework.util.Assert;

public record QuestCompleteRequest(Long userId, Long questId) {

    public QuestCompleteRequest {
        Assert.notNull(userId, "userId는 null이 될 수 없습니다");
        Assert.notNull(questId, "questId는 null이 될 수 없습니다");
    }
}
