package com.example.sunshineserver.quest.infrastructure;

import com.example.sunshineserver.quest.application.QuestService;
import com.example.sunshineserver.quest.domain.QuestTemplate;
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

    private final QuestService questService;
    private final UserService userService;
    private final QuestTemplateRepository questTemplateRepository;

    // 매일 자정에 실행됩니다.
    @Scheduled(cron = "0 0 0 * * *")
    public void assignQuestDaily() {
        LocalDate currentDate = LocalDate.now();

        userService.findAllUsers().stream().forEach(user -> {
            LocalDate userCreationDate = user.getCreatedAt().toLocalDate();
            QuestTemplate questTemplate = determineQuestTemplate(userCreationDate, currentDate);

            questService.assignQuest(user, questTemplate);
        });
    }

    private QuestTemplate determineQuestTemplate(LocalDate userCreationDate,
        LocalDate currentDate) {
        Period period = Period.between(userCreationDate, currentDate);
        int questionDay = period.getDays();

        return questTemplateRepository.findByQuestionDay(questionDay)
            .orElseThrow(() -> new IllegalArgumentException("퀘스트 템플릿이 존재하지 않습니다."));
    }
}
