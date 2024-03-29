package com.example.sunshineserver.quest.presentation;

import com.example.sunshineserver.auth.domain.CustomUserDetails;
import com.example.sunshineserver.global.domain.RequestUri;
import com.example.sunshineserver.quest.application.QuestService;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.presentation.dto.QuestCompleteResponse;
import com.example.sunshineserver.quest.presentation.dto.QuestDetailResponse;
import com.example.sunshineserver.quest.presentation.dto.ShortAnswerQuestCompleteRequest;
import com.example.sunshineserver.saramin.presentation.dto.SaraminJobResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping(RequestUri.QUEST)
@Tag(name = "Quest", description = "Quest API")
public class QuestController {

    private final QuestService questService;

    @GetMapping("/{user_quest_id}")
    @Operation(summary = "퀘스트 상세 조회", description = "퀘스트의 상세 정보를 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다."),
    })

    public ResponseEntity<QuestDetailResponse> findQuestDetail(
        @PathVariable(name = "user_quest_id") Long questId) {
        return new ResponseEntity<>(questService.findQuestDetail(questId), HttpStatus.OK);
    }

    @PostMapping("/{user_quest_id}")
    @Operation(summary = "퀘스트 완료", description = "퀘스트를 완료합니다. 유저의 경험치와 스탯이 증가합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "실패하였습니다."),
    })
    public ResponseEntity<QuestCompleteResponse> completeQuest(
        @PathVariable(name = "user_quest_id") Long questId,
        @AuthenticationPrincipal
        CustomUserDetails userDetails) {
        return new ResponseEntity<>(questService.complete(questId, userDetails), HttpStatus.OK);
    }

    @PostMapping("/short-answer/{user_quest_id}")
    @Operation(summary = "주관식 퀘스트 완료", description = "주관식 퀘스트를 완료합니다. 유저의 경험치와 스탯이 증가합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "실패하였습니다."),
    })
    public ResponseEntity<QuestCompleteResponse> completeShortAnswerQuest(
        @PathVariable(name = "user_quest_id") Long questId,
        @RequestBody ShortAnswerQuestCompleteRequest request,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return new ResponseEntity<>(
            questService.completeShortAnswerQuest(questId, request, userDetails), HttpStatus.OK);
    }

    @PostMapping("/photo/{user_quest_id}")
    @Operation(summary = "사진 퀘스트 완료", description =
        "사진 퀘스트를 완료합니다. 유저의 경험치와 스탯이 증가합니다. 이 때, 사진은 multipart/form으로 전송해야 합니다. 전송 후 반환되는 uuid 값을 통해 사진에 접근 가능합니다."
            + "https://storage.googleapis.com/sunshine-bucket/UUID값")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "실패하였습니다."),
    })
    public ResponseEntity<QuestCompleteResponse> completePhotoQuest(
        @PathVariable(name = "user_quest_id") Long questId,
        @RequestPart(name = "photo") MultipartFile photo,
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return new ResponseEntity<>(questService.completePhotoQuest(questId, photo, userDetails)
            , HttpStatus.OK);
    }

    @GetMapping("/uncompleted")
    @Operation(summary = "수행 중인 미션 목록을 조회", description = "수행 중인, 즉 완료 되지 않은 미션을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다,"),
    })
    public ResponseEntity<List<UserQuest>> findUncompletedQuests(
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return new ResponseEntity<>(questService.findUncompletedQuests(userDetails), HttpStatus.OK);
    }

    @GetMapping("/completed")
    @Operation(summary = "완료한 미션 목록을 조회", description = "완료한 미션을 조회합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다."),
    })
    public ResponseEntity<List<UserQuest>> findCompletedQuests(
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return new ResponseEntity<>(questService.findCompletedQuests(userDetails), HttpStatus.OK);
    }

    @GetMapping("/final")
    @Operation(summary = "마지막 미션을 조회", description = "마지막 미션인 직업 추천을 진행합니다.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "성공"),
        @ApiResponse(responseCode = "404", description = "조회에 실패하였습니다."),
    })
    public ResponseEntity<List<SaraminJobResponse>> findFinalQuest(
        @AuthenticationPrincipal CustomUserDetails userDetails) {
        return new ResponseEntity<>(questService.findFinalQuest(userDetails), HttpStatus.OK);
    }
}
