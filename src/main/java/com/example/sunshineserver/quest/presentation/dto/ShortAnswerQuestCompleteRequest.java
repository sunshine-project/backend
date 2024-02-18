package com.example.sunshineserver.quest.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ShortAnswerQuestCompleteRequest(
    @NotBlank String answer) {

}
