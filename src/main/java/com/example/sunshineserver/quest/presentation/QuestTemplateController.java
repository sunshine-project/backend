package com.example.sunshineserver.quest.presentation;

import com.example.sunshineserver.auth.domain.CustomUserDetails;
import com.example.sunshineserver.global.domain.RequestUri;
import com.example.sunshineserver.quest.application.QuestTemplateService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(RequestUri.QUEST)
@Tag(name = "QuestTemplate", description = "QuestTemplate API")
public class QuestTemplateController {

    private final QuestTemplateService questTemplateService;
    @PostMapping
    public ResponseEntity<Void> assignQuestTemplate(@RequestBody int questionDay, @AuthenticationPrincipal
    CustomUserDetails userDetails) {
        questTemplateService.assignQuestTemplate(questionDay, userDetails);
        return ResponseEntity.ok().build();
    }
}
