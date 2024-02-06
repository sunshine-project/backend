package com.example.sunshineserver.quest.presentation;

import com.example.sunshineserver.global.domain.RequestURI;
import com.example.sunshineserver.quest.application.QuestService;
import com.example.sunshineserver.quest.presentation.dto.QuestCompleteRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(RequestURI.QUEST)
@Tag(name = "Quest", description = "Quest API")
public class QuestController {

    private final QuestService questService;

    @PostMapping
    @Operation(summary = "퀘스트 완료", description = "퀘스트를 완료합니다. 유저의 경험치와 스탯이 증가합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "회원 가입에 실패하였습니다."),
    })
    public void complete(@RequestBody QuestCompleteRequest request) {
        questService.complete(request);
    }
}
