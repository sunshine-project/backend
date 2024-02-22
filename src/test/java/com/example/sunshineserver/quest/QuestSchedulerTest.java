package com.example.sunshineserver.quest;

import com.example.sunshineserver.auth.domain.CustomUserDetails;
import com.example.sunshineserver.quest.application.QuestService;
import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.QuestionType;
import com.example.sunshineserver.quest.domain.StatInfo;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.QuestTemplateRepository;
import com.example.sunshineserver.quest.infrastructure.QuestScheduler;
import com.example.sunshineserver.user.UserSteps;
import com.example.sunshineserver.user.application.UserService;
import com.example.sunshineserver.user.domain.ExperiencePoint;
import com.example.sunshineserver.user.domain.StatType;
import com.example.sunshineserver.user.domain.repository.UserPort;
import com.example.sunshineserver.user.presentation.dto.UserCreateResponse;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.assertj.core.api.Assertions;
import org.awaitility.Awaitility;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
    "schedules.cron.reward.publish=0/2 * * * * ?"
})
public class QuestSchedulerTest {

    @Autowired
    private UserService userService;

    @Autowired
    private QuestService questService;

    @Autowired
    private UserPort userPort;

    @BeforeEach
    void setUp() {
        userPort.deleteAll();
    }

    @Test
    void 지정된_시간이_지나면_퀘스트가_자동으로_할당된다() {
        // given
        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());
        CustomUserDetails userDetails = new CustomUserDetails(
            userPort.findByEmail(userCreateResponse.email()).orElseThrow(RuntimeException::new));

        // when
        List<UserQuest> quests = questService.findUncompletedQuests(userDetails);
        Assertions.assertThat(quests).hasSize(0);

        Awaitility.await().atMost(3, TimeUnit.SECONDS).untilAsserted(() -> {
            List<UserQuest> newQuests = questService.findUncompletedQuests(userDetails);
            Assertions.assertThat(newQuests).hasSize(1);
        });
    }
}
