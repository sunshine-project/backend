package com.example.sunshineserver.quest.application;

import com.example.sunshineserver.global.exception.QuestAlreadyAssignedException;
import com.example.sunshineserver.global.exception.UserNotFoundedException;
import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.QuestionType;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.QuestTemplateRepository;
import com.example.sunshineserver.quest.domain.repository.UserQuestPort;
import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserPort;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AssignQuestService {

    private final UserPort userPort;
    private final UserQuestPort userQuestPort;
    private final QuestTemplateRepository questTemplateRepository;

    public void assignQuest(User user, QuestTemplate questTemplate) {
        if (userQuestPort.existsByQuestTemplateAndUser(questTemplate, user)) {
            throw new QuestAlreadyAssignedException();
        }
        userQuestPort.save(UserQuest.of(questTemplate, user));
    }

    public void assignRoutineQuest(User user) {
        List<QuestTemplate> routineQuests = questTemplateRepository.findAllByQuestionType(
            QuestionType.ROUTINE);

        for (QuestTemplate questTemplate : routineQuests) {
            userQuestPort.save(UserQuest.of(questTemplate, user));
        }
    }

    public void assignInitialQuest(Long userId) {
        User user = userPort.findById(userId)
            .orElseThrow(UserNotFoundedException::new);

        int firstDay = 1;

        QuestTemplate questTemplate = questTemplateRepository.findByQuestionDay(firstDay)
            .orElseThrow(IllegalArgumentException::new);

        if (userQuestPort.existsByQuestTemplateAndUser(questTemplate, user)) {
            throw new QuestAlreadyAssignedException();
        }

        userQuestPort.save(UserQuest.of(questTemplate, user));

        List<QuestTemplate> routineQuests = questTemplateRepository.findAllByQuestionType(
            QuestionType.ROUTINE);

        for (QuestTemplate routineQuest : routineQuests) {
            userQuestPort.save(UserQuest.of(routineQuest, user));
        }
    }
}
