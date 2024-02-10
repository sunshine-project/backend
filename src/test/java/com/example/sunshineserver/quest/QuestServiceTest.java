package com.example.sunshineserver.quest;

import com.example.sunshineserver.quest.application.QuestService;
import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.QuestionType;
import com.example.sunshineserver.quest.domain.StatInfo;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.QuestTemplateRepository;
import com.example.sunshineserver.quest.domain.repository.UserQuestPort;
import com.example.sunshineserver.quest.presentation.dto.CompletedQuestsInquiryRequest;
import com.example.sunshineserver.quest.presentation.dto.QuestCompleteRequest;
import com.example.sunshineserver.quest.presentation.dto.QuestDetailRequest;
import com.example.sunshineserver.quest.presentation.dto.QuestDetailResponse;
import com.example.sunshineserver.quest.presentation.dto.UncheckedQuestsInquiryRequest;
import com.example.sunshineserver.quest.presentation.dto.UncompletedQuestsInquiryRequest;
import com.example.sunshineserver.user.UserSteps;
import com.example.sunshineserver.user.application.UserService;
import com.example.sunshineserver.user.domain.ExperiencePoint;
import com.example.sunshineserver.user.domain.StatType;
import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserPort;
import com.example.sunshineserver.user.presentation.dto.UserCreateResponse;
import java.util.List;
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
        userQuestPort.deleteAll();
        questTemplateRepository.deleteAll();
    }

    @Test
    void 퀘스트를_완료하면_유저의_경험치와_스탯이_증가한다() {
        // given
        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());
        QuestTemplate questTemplate = 테스트_퀘스트_생성();

        User user = userPort.findById(userCreateResponse.userId())
            .orElseThrow(RuntimeException::new);
        Long userQuestId = userQuestPort.save(UserQuest.of(questTemplate, user));

        QuestCompleteRequest request = new QuestCompleteRequest(userCreateResponse.userId(),
            userQuestId);

        // when
        questService.complete(request);

        // then
        User findUser = userPort.findById(userCreateResponse.userId()).orElseThrow(RuntimeException::new);
        Assertions.assertThat(findUser.getExperiencePoint().get()).isEqualTo(100);
    }

    @Test
    void 이미_완료한_퀘스트는_예외를_발생한다() {
        // given
        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());
        QuestTemplate questTemplate = 테스트_퀘스트_생성();

        User user = userPort.findById(userCreateResponse.userId())
            .orElseThrow(RuntimeException::new);
        Long userQuestId = userQuestPort.save(UserQuest.of(questTemplate, user));

        QuestCompleteRequest request = new QuestCompleteRequest(userCreateResponse.userId(),
            userQuestId);

        questService.complete(request);

        // when & then
        Assertions.assertThatThrownBy(() -> questService.complete(request))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 한번도_조회되지_않은_퀘스트를_조회한다() {
        // given
        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());
        복수의_테스트_퀘스트_생성(userCreateResponse.userId());

        UncheckedQuestsInquiryRequest request = new UncheckedQuestsInquiryRequest(
            userCreateResponse.userId());

        // when
        List<UserQuest> quests = questService.findUncheckedQuests(request);

        // then
        Assertions.assertThat(quests.size()).isEqualTo(3);
    }

    @Test
    void 조건에_따라_퀘스트들을_조회한다() {
        // given
        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());
        복수의_테스트_퀘스트_생성(userCreateResponse.userId());

        List<UserQuest> quests = userQuestPort.findAll();
        QuestCompleteRequest request = new QuestCompleteRequest(userCreateResponse.userId(), quests.get(0).getId());
        CompletedQuestsInquiryRequest completedQuestsInquiryRequest = new CompletedQuestsInquiryRequest(
            userCreateResponse.userId());
        UncompletedQuestsInquiryRequest uncompletedQuestsInquiryRequest = new UncompletedQuestsInquiryRequest(
            userCreateResponse.userId());
        // when
        questService.complete(request);

        // then
        // 완료한 퀘스트 1개, 미완료한 퀘스트 2개
        Assertions.assertThat(
            questService.findCompletedQuests(completedQuestsInquiryRequest).size()).isEqualTo(1);
        Assertions.assertThat(
	questService.findUncompletedQuests(uncompletedQuestsInquiryRequest).size())
            .isEqualTo(2);
    }

    @Test
    void 퀘스트_상세_내용을_조회한다() {
        // given
        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());
        QuestTemplate questTemplate = 테스트_퀘스트_생성();

        User user = userPort.findById(userCreateResponse.userId())
            .orElseThrow(RuntimeException::new);
        Long userQuestId = userQuestPort.save(UserQuest.of(questTemplate, user));

        QuestDetailRequest request = new QuestDetailRequest(userQuestId);

        // when
        QuestDetailResponse questDetailResponse = questService.findQuestDetail(request);

        // then
        Assertions.assertThat(questDetailResponse.title()).isEqualTo("스트레칭");
        Assertions.assertThat(questDetailResponse.description()).isEqualTo("30초 동안 스트레칭을 실시하세요");
        Assertions.assertThat(questDetailResponse.experiencePoint().get()).isEqualTo(100);
        Assertions.assertThat(questDetailResponse.statInfo().getStatType()).isEqualTo(StatType.STR);
        Assertions.assertThat(questDetailResponse.statInfo().getStatValue()).isEqualTo(1);
        Assertions.assertThat(questDetailResponse.questionType()).isEqualTo(QuestionType.TIMER);
        Assertions.assertThat(questDetailResponse.timeLimit()).isEqualTo(15);
    }

    private QuestTemplate 테스트_퀘스트_생성() {
        QuestTemplate questTemplate = new QuestTemplate(1, "스트레칭", "30초 동안 스트레칭을 실시하세요",
            ExperiencePoint.from(100), QuestionType.TIMER,
            StatInfo.of(StatType.STR, 1), 15, false, false);

        return questTemplate;
    }

    private void 복수의_테스트_퀘스트_생성(Long userId) {
        QuestTemplate quest1 = new QuestTemplate(1, "스트레칭", "30초 동안 스트레칭을 실시하세요",
            ExperiencePoint.from(100), QuestionType.TIMER,
            StatInfo.of(StatType.STR, 1), 15, false, false);

        QuestTemplate quest2 = new QuestTemplate(2, "이불 정리 하기", "이불을 정리하고 인증하세요",
            ExperiencePoint.from(200), QuestionType.ROUTINE,
            StatInfo.of(StatType.STR, 1), null, true, false);

        QuestTemplate quest3 = new QuestTemplate(3, "책 읽기", "책을 30분 동안 읽고 인증하세요",
            ExperiencePoint.from(300), QuestionType.TIMER,
            StatInfo.of(StatType.SPI, 1), 30, false, false);

        userPort.findById(userId).ifPresent(user -> {
            userQuestPort.save(UserQuest.of(quest1, user));
            userQuestPort.save(UserQuest.of(quest2, user));
            userQuestPort.save(UserQuest.of(quest3, user));
        });
    }
}
