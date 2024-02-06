package com.example.sunshineserver.quest.presentation;

import com.example.sunshineserver.global.domain.RequestURI;
import com.example.sunshineserver.quest.application.QuestService;
import com.example.sunshineserver.quest.presentation.dto.QuestCompleteRequest;
import com.example.sunshineserver.quest.presentation.dto.QuestDetailRequest;
import com.example.sunshineserver.quest.presentation.dto.QuestDetailResponse;
import com.example.sunshineserver.quest.presentation.dto.UncompletedQuestsInquiryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/{questId}")
    @Operation(summary = "퀘스트 상세 조회", description = "퀘스트의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다."),
    })

    public QuestDetailResponse findQuestDetail(@RequestBody QuestDetailRequest request) {
        return questService.findQuestDetail(request);
    }

    @PostMapping
    @Operation(summary = "퀘스트 완료", description = "퀘스트를 완료합니다. 유저의 경험치와 스탯이 증가합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "실패하였습니다."),
    })
    public void completeQuest(@RequestBody QuestCompleteRequest request) {
        questService.complete(request);
    }

    @GetMapping("/uncompleted")
    @Operation(summary = "수행 중인 미션 목록을 조회", description = "수행 중인, 즉 완료 되지 않은 미션을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다,"),
    })
    public void findUncompletedQuests(@RequestBody UncompletedQuestsInquiryRequest request) {
        questService.findUncompletedQuests(request);
    }

    @GetMapping("/completed")
    @Operation(summary = "완료한 미션 목록을 조회", description = "완료한 미션을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다."),
    })
    public void findCompletedQuests(@RequestBody UncompletedQuestsInquiryRequest request) {
        questService.findUncompletedQuests(request);
    }
}
