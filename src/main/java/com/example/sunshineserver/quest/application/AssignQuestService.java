package com.example.sunshineserver.quest.application;

import com.example.sunshineserver.global.exception.QuestAlreadyAssignedException;
import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.QuestionType;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.QuestTemplateRepository;
import com.example.sunshineserver.quest.domain.repository.UserQuestPort;
import com.example.sunshineserver.user.domain.User;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AssignQuestService {

    private final UserQuestPort userQuestPort;
    private final QuestTemplateRepository questTemplateRepository;

    public void assignQuest(User user, QuestTemplate questTemplate) {
        if (userQuestPort.existsByQuestTemplateAndUser(questTemplate, user)) {
            throw new QuestAlreadyAssignedException();
        }
        userQuestPort.save(UserQuest.of(questTemplate, user));
    }

    public void assignDailyQuest(User user) {
        List<QuestTemplate> routineQuests = questTemplateRepository.findAllByQuestionType(
            QuestionType.ROUTINE);

        for (QuestTemplate questTemplate : routineQuests) {
            userQuestPort.save(UserQuest.of(questTemplate, user));
        }
    }
}
