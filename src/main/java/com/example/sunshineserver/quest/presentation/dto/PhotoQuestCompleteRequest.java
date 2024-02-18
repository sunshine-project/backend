package com.example.sunshineserver.quest.presentation.dto;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

public record PhotoQuestCompleteRequest(@NotNull Long questId, @NotNull
MultipartFile photo) {

}
