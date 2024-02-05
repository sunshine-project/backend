package com.example.sunshineserver.quest.application;

import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.UserQuestPort;
import com.example.sunshineserver.quest.presentation.dto.QuestCompleteRequest;
import jakarta.transaction.Transactional;
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
}