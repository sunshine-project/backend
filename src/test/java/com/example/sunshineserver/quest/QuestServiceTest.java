package com.example.sunshineserver.quest;

import com.example.sunshineserver.auth.domain.CustomUserDetails;
import com.example.sunshineserver.quest.application.QuestService;
import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.QuestionType;
import com.example.sunshineserver.quest.domain.StatInfo;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.QuestTemplateRepository;
import com.example.sunshineserver.quest.domain.repository.UserQuestPort;
import com.example.sunshineserver.quest.presentation.dto.QuestDetailResponse;
import com.example.sunshineserver.quest.presentation.dto.ShortAnswerQuestCompleteRequest;
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

        User user = userPort.findByEmail(userCreateResponse.email())
            .orElseThrow(RuntimeException::new);

        Long userQuestId = userQuestPort.save(UserQuest.of(questTemplate, user));

        // when
        questService.complete(userQuestId, new CustomUserDetails(user));

        // then
        User findUser = userPort.findByEmail(userCreateResponse.email())
            .orElseThrow(RuntimeException::new);
        Assertions.assertThat(findUser.getExperiencePoint().get()).isEqualTo(100);
    }

    @Test
    void 이미_완료한_퀘스트는_예외를_발생한다() {
        // given
        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());
        QuestTemplate questTemplate = 테스트_퀘스트_생성();

        User user = userPort.findByEmail(userCreateResponse.email())
            .orElseThrow(RuntimeException::new);
        Long userQuestId = userQuestPort.save(UserQuest.of(questTemplate, user));

        questService.complete(userQuestId, new CustomUserDetails(user));

        // when & then
        Assertions.assertThatThrownBy(
	() -> questService.complete(userQuestId, new CustomUserDetails(user))
            )
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void 조건에_따라_퀘스트들을_조회한다() {
        // given
        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());
        복수의_테스트_퀘스트_생성(userCreateResponse.email());

        CustomUserDetails userDetails = new CustomUserDetails(
            userPort.findByEmail(userCreateResponse.email()).orElseThrow(RuntimeException::new));

        Assertions.assertThat(
	questService.findUncompletedQuests(userDetails).size())
            .isEqualTo(3);
    }

    @Test
    void 퀘스트_상세_내용을_조회한다() {
        // given
        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());
        QuestTemplate questTemplate = 테스트_퀘스트_생성();

        User user = userPort.findByEmail(userCreateResponse.email())
            .orElseThrow(RuntimeException::new);
        Long userQuestId = userQuestPort.save(UserQuest.of(questTemplate, user));

        // when
        QuestDetailResponse questDetailResponse = questService.findQuestDetail(userQuestId);

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
        return questTemplateRepository.save(new QuestTemplate(1, "스트레칭", "30초 동안 스트레칭을 실시하세요",
            ExperiencePoint.from(100), QuestionType.TIMER,
            StatInfo.of(StatType.STR, 1), 15));
    }

    private void 복수의_테스트_퀘스트_생성(String email) {
        QuestTemplate quest1 = new QuestTemplate(1, "스트레칭", "30초 동안 스트레칭을 실시하세요",
            ExperiencePoint.from(100), QuestionType.TIMER,
            StatInfo.of(StatType.STR, 1), 15);

        QuestTemplate quest2 = new QuestTemplate(2, "이불 정리 하기", "이불을 정리하고 인증하세요",
            ExperiencePoint.from(200), QuestionType.ROUTINE,
            StatInfo.of(StatType.STR, 1), null);

        QuestTemplate quest3 = new QuestTemplate(3, "책 읽기", "책을 30분 동안 읽고 인증하세요",
            ExperiencePoint.from(300), QuestionType.TIMER,
            StatInfo.of(StatType.SPI, 1), 30);

        questTemplateRepository.save(quest1);
        questTemplateRepository.save(quest2);
        questTemplateRepository.save(quest3);

        userPort.findByEmail(email).ifPresent(user -> {
            userQuestPort.save(UserQuest.of(quest1, user));
            userQuestPort.save(UserQuest.of(quest2, user));
            userQuestPort.save(UserQuest.of(quest3, user));
        });
    }

    @Test
    void SHORT_ANSWER_유형의_퀘스트를_진행한다() {
        // given
        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());
        CustomUserDetails userDetails = new CustomUserDetails(
            userPort.findByEmail(userCreateResponse.email()).orElseThrow(RuntimeException::new));

        QuestTemplate questTemplate = SHORT_ANSWER_테스트_퀘스트_생성();
        Long userQuestId = userQuestPort.save(UserQuest.of(questTemplate,
            userPort.findByEmail(userCreateResponse.email()).orElseThrow(RuntimeException::new)));

        String answer = "답변";

        ShortAnswerQuestCompleteRequest request = new ShortAnswerQuestCompleteRequest(answer);

        // when
        questService.completeShortAnswerQuest(userQuestId, request, userDetails);

        // then
        User user = userPort.findByEmail(userCreateResponse.email())
            .orElseThrow(RuntimeException::new);
        UserQuest userQuest = userQuestPort.findById(userQuestId)
            .orElseThrow(RuntimeException::new);

        Assertions.assertThat(user.getExperiencePoint().get()).isEqualTo(300);
        Assertions.assertThat(user.getStat().getSpi()).isEqualTo(2);
        Assertions.assertThat(userQuest.getShortAnswer()).isEqualTo(answer);
        Assertions.assertThat(userQuest.isCompleted()).isTrue();
        Assertions.assertThat(userQuest.getShortAnswer()).isEqualTo(answer);
    }

    private QuestTemplate SHORT_ANSWER_테스트_퀘스트_생성() {
        return questTemplateRepository.save(new QuestTemplate(1, "당신의 꿈은?", "당신의 꿈을 구체적으로 설명해주세요.",
            ExperiencePoint.from(300), QuestionType.SHORT_ANSWER,
            StatInfo.of(StatType.SPI, 1), null));
    }

//    @Test
//    void PHOTO_유형의_퀘스트를_진행한다() {
//        // given
//        UserCreateResponse userCreateResponse = userService.create(UserSteps.유저_생성_요청());
//        CustomUserDetails userDetails = new CustomUserDetails(
//            userPort.findByEmail(userCreateResponse.email()).orElseThrow(RuntimeException::new));
//
//        QuestTemplate questTemplate = SHORT_ANSWER_테스트_퀘스트_생성();
//        Long userQuestId = userQuestPort.save(UserQuest.of(questTemplate,
//            userPort.findByEmail(userCreateResponse.email()).orElseThrow(RuntimeException::new)));
//
//        String answer = "답변";
//
//        ShortAnswerQuestCompleteRequest request = new ShortAnswerQuestCompleteRequest(
//            userQuestId, answer);
//
//        // when
//        questService.completePhotoQuest(userQuestId, userDetails);
//
//        // then
//        User user = userPort.findByEmail(userCreateResponse.email())
//            .orElseThrow(RuntimeException::new);
//        UserQuest userQuest = userQuestPort.findById(userQuestId)
//            .orElseThrow(RuntimeException::new);
//
//        Assertions.assertThat(user.getExperiencePoint().get()).isEqualTo(300);
//        Assertions.assertThat(user.getStat().getSpi()).isEqualTo(2);
//        Assertions.assertThat(userQuest.getShortAnswer()).isEqualTo(answer);
//        Assertions.assertThat(userQuest.isCompleted()).isTrue();
//        Assertions.assertThat(userQuest.getShortAnswer()).isEqualTo(answer);
//    }

    private QuestTemplate PHOTO_테스트_퀘스트_생성() {
        return new QuestTemplate(1, "당신의 꿈은?", "당신의 꿈을 구체적으로 설명해주세요.",
            ExperiencePoint.from(300), QuestionType.SHORT_ANSWER,
            StatInfo.of(StatType.SPI, 1), null);
    }
}
