package com.example.sunshineserver.quest.application;

import com.example.sunshineserver.auth.domain.CustomUserDetails;
import com.example.sunshineserver.global.exception.UserNotFoundedException;
import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.QuestTemplateRepository;
import com.example.sunshineserver.quest.domain.repository.UserQuestRepository;
import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class QuestTemplateService {

    private final QuestTemplateRepository questTemplateRepository;
    private final UserPort userPort;
    private final UserQuestRepository userQuestRepository;

    public void assignQuestTemplate(int questionDay, CustomUserDetails userDetails) {
        User user = userPort.findByEmail(userDetails.getEmail())
            .orElseThrow(UserNotFoundedException::new);

        QuestTemplate questTemplate = questTemplateRepository.findByQuestionDay(questionDay)
            .orElseThrow(IllegalArgumentException::new);

        if (userQuestRepository.existsByQuestTemplateAndUser(questTemplate, user)) {
            throw new IllegalStateException("이미 퀘스트가 할당되었습니다.");
        }

        userQuestRepository.save(UserQuest.of(questTemplate, user));
    }
}
