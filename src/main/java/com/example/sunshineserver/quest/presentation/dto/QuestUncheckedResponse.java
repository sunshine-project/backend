package com.example.sunshineserver.quest.presentation.dto;

public record QuestUncheckedResponse(int size) {

    public static QuestUncheckedResponse of(int size) {
        return new QuestUncheckedResponse(size);
    }
}
