package com.example.sunshineserver.quest.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ShortAnswerQuestCompleteRequest(@NotNull Long userId, @NotNull Long questId,
		              @NotBlank String answer) {

}
