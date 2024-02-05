package com.example.sunshineserver.quest;

import com.example.sunshineserver.quest.application.QuestService;
import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.QuestionType;
import com.example.sunshineserver.quest.domain.StatInfo;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.QuestTemplateRepository;
import com.example.sunshineserver.quest.domain.repository.UserQuestPort;
import com.example.sunshineserver.quest.presentation.dto.QuestCompleteRequest;
import com.example.sunshineserver.user.UserSteps;
import com.example.sunshineserver.user.application.UserService;
import com.example.sunshineserver.user.domain.ExperiencePoint;
import com.example.sunshineserver.user.domain.StatType;
import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserPort;
import jakarta.annotation.PostConstruct;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class QuestServiceTest {

    @Autowired
    private QuestService questService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserPort userPort;

    @Autowired
    private UserQuestPort userQuestPort;

    @Autowired
    QuestTemplateRepository questTemplateRepository;

    @BeforeEach
    void setUp() {
        userPort.deleteAll();
        questTemplateRepository.deleteAll();
    }

    @Test
    void 퀘스트를_완료하면_유저의_경험치와_스탯이_증가한다() {
        // given
        Long userId = userService.create(UserSteps.유저_생성_요청());
        Long questId = 테스트_퀘스트_생성();

        User user = userPort.findById(userId).orElseThrow(RuntimeException::new);
        QuestTemplate questTemplate = questTemplateRepository.findById(questId)
            .orElseThrow(RuntimeException::new);

        Long userQuestId = userQuestPort.save(UserQuest.of(questTemplate, user));

        QuestCompleteRequest request = new QuestCompleteRequest(userId, userQuestId);

        // when
        questService.completeQuest(request);

        // then
        User findUser = userPort.findById(userId).orElseThrow(RuntimeException::new);
        Assertions.assertThat(findUser.getExperiencePoint().get()).isEqualTo(100);
    }

    @Test
    void 이미_완료한_퀘스트는_예외를_발생한다() {
        // given
        Long userId = userService.create(UserSteps.유저_생성_요청());
        Long questId = 테스트_퀘스트_생성();

        User user = userPort.findById(userId).orElseThrow(RuntimeException::new);
        QuestTemplate questTemplate = questTemplateRepository.findById(questId)
            .orElseThrow(RuntimeException::new);

        Long userQuestId = userQuestPort.save(UserQuest.of(questTemplate, user));

        QuestCompleteRequest request = new QuestCompleteRequest(userId, userQuestId);

        questService.completeQuest(request);

        // when & then
        Assertions.assertThatThrownBy(() -> questService.completeQuest(request))
            .isInstanceOf(IllegalStateException.class);
    }

    private Long 테스트_퀘스트_생성() {
        QuestTemplate quest1 = new QuestTemplate(1, "스트레칭", "30초 동안 스트레칭을 실시하세요",
            ExperiencePoint.from(100), QuestionType.TIMER,
            StatInfo.of(StatType.STR, 1), 15, false, false);

//        QuestTemplate quest2 = new QuestTemplate(2, "이불 정리 하기", "이불을 정리하고 인증하세요",
//            ExperiencePoint.from(200), QuestionType.ROUTINE,
//            StatInfo.of(StatType.STR, 1), null, true, false);

        return questTemplateRepository.save(quest1).getId();
//        questTemplateRepository.save(quest2);
    }
}
