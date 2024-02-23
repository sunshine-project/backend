package com.example.sunshineserver.quest.infrastructure;

import com.example.sunshineserver.quest.application.AssignQuestService;
import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.QuestTimer;
import com.example.sunshineserver.quest.domain.repository.QuestTemplateRepository;
import com.example.sunshineserver.user.application.UserService;
import java.time.LocalDate;
import java.time.Period;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class QuestScheduler {

    private final AssignQuestService assignQuestService;
    private final UserService userService;
    private final QuestTemplateRepository questTemplateRepository;
    private final QuestTimer questTimer;

    private final int twoDays = 2;

    @Scheduled(cron = "${schedules.cron.reward.publish}")
    public void assignQuestDaily() {
        LocalDate currentDate = questTimer.now();

        userService.findAllUsers().stream().forEach(user -> {
            LocalDate userCreationDate = user.getCreatedAt().toLocalDate();
            QuestTemplate questTemplate = determineQuestTemplate(userCreationDate, currentDate);

            assignQuestService.assignQuest(user, questTemplate);
            assignQuestService.assignRoutineQuest(user);
        });
    }

    private QuestTemplate determineQuestTemplate(LocalDate userCreationDate,
        LocalDate currentDate) {
        Period period = Period.between(userCreationDate, currentDate);
        int questionDay = period.getDays() + twoDays;

        return questTemplateRepository.findByQuestionDay(questionDay)
            .orElseThrow(() -> new IllegalArgumentException("퀘스트 템플릿이 존재하지 않습니다."));
    }
}
