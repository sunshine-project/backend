package com.example.sunshineserver.quest.application;

import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.UserQuestPort;
import com.example.sunshineserver.quest.presentation.dto.CompletedQuestsInquiryRequest;
import com.example.sunshineserver.quest.presentation.dto.QuestCompleteRequest;
import com.example.sunshineserver.quest.presentation.dto.UncheckedQuestsInquiryRequest;
import com.example.sunshineserver.quest.presentation.dto.UncompletedQuestsInquiryRequest;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestService {

    private final UserQuestPort userQuestPort;

    public void completeQuest(QuestCompleteRequest request) {
        UserQuest userQuest = userQuestPort.findById(request.questId())
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 퀘스트입니다."));

        userQuest.complete();
    }

    public List<UserQuest> findUncheckedQuests(UncheckedQuestsInquiryRequest request) {
        List<UserQuest> userQuests = userQuestPort.findUncheckedQuests(request.userId());

        userQuests.stream()
            .forEach(userQuest -> userQuest.check());

        return userQuests;
    }

    public List<UserQuest> findCompletedQuests(CompletedQuestsInquiryRequest request) {
        return userQuestPort.findCompletedQuests(request.userId());
    }

    public List<UserQuest> findUncompletedQuests(UncompletedQuestsInquiryRequest request) {
        return userQuestPort.findUncompletedQuests(request.userId());
    }
}
