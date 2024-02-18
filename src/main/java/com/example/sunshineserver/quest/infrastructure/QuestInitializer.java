package com.example.sunshineserver.quest.infrastructure;

import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.QuestionType;
import com.example.sunshineserver.quest.domain.StatInfo;
import com.example.sunshineserver.quest.domain.repository.QuestTemplateRepository;
import com.example.sunshineserver.user.domain.ExperiencePoint;
import com.example.sunshineserver.user.domain.StatType;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class QuestInitializer {

    private final QuestTemplateRepository questTemplateRepository;

    @PostConstruct
    @Transactional
    public void initialize() {
        questTemplateRepository.save(new QuestTemplate(1, "5분 간 명상하기", "5분 동안 명상하고 마음을 진정시키세요.",
            ExperiencePoint.from(10), QuestionType.TIMER,
            StatInfo.of(StatType.STR, 1), 1));

        questTemplateRepository.save(new QuestTemplate(2, "먼 산 보기", "먼 곳을 바라보며 눈의 피로를 풀어주세요.",
            ExperiencePoint.from(10), QuestionType.DAILY,
            StatInfo.of(StatType.SPI, 1), 1));

        questTemplateRepository.save(new QuestTemplate(3, "하늘 사진 찍기", "아름다운 하늘을 사진으로 남겨보세요.",
            ExperiencePoint.from(10), QuestionType.PHOTO,
            StatInfo.of(StatType.SPI, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(4, "좋아하는 색 옷 입어보기", "자신이 좋아하는 색의 옷을 입고 사진을 찍어보세요.",
	ExperiencePoint.from(10), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(5, "책 10페이지 읽기", "책을 10페이지 읽고 새로운 지식을 얻어보세요.",
	ExperiencePoint.from(10), QuestionType.PHOTO,
	StatInfo.of(StatType.SKL, 1), 1));

        questTemplateRepository.save(new QuestTemplate(6, "창문 열고 햇빛 쬐기", "창문을 열고 10분간 햇빛을 쬐어보세요.",
            ExperiencePoint.from(10), QuestionType.TIMER,
            StatInfo.of(StatType.SPI, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(7, "내 자신을 가꾸기", "자기 자신을 가꾸는 활동을 하고 그 과정이나 결과를 사진으로 남겨보세요.",
	ExperiencePoint.from(10), QuestionType.PHOTO,
	StatInfo.of(StatType.STR, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(8, "좋아하는 음식 먹기", "자신이 좋아하는 음식을 먹고 그 순간을 사진으로 남겨보세요.",
	ExperiencePoint.from(10), QuestionType.PHOTO,
	StatInfo.of(StatType.SPI, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(9, "나가서 15분 이상 걷기", "실외에서 15분 이상 걸으며 신선한 공기를 마셔보세요.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 1), 2));

        questTemplateRepository.save(new QuestTemplate(10, "책 또는 영화/드라마를 보고 좋았던 문장과 이유를 적어보기",
            "책이나 영화, 드라마에서 인상 깊었던 문장을 적고, 그 이유를 설명해보세요.",
            ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
            StatInfo.of(StatType.SKL, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(11, "만들어 보고 싶은 음식 떠올리기", "만들어 보고 싶은 음식을 떠올리고, 그 이유나 레시피를 간단히 적어보세요.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.PEA, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(12, "좋아하는 노래 생각하기", "자신이 좋아하는 노래를 떠올리고, 그 노래가 왜 좋은지 이유를 적어보세요.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(13, "스타벅스에서 커피 주문하기", "스타벅스에 가서 좋아하는 커피를 주문하고, 주문한 커피의 사진을 찍어보세요.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.PEA, 1), 2));

        questTemplateRepository.save(
            new QuestTemplate(14, "필통 정리하기", "자신의 필통을 정리하고, 정리 전후의 사진을 찍어 비교해보세요.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.SKL, 1), 1));

        questTemplateRepository.save(new QuestTemplate(15, "친구나 지인에게 안부 연락하기",
            "친구나 지인에게 안부를 묻는 메시지를 보내고, 그 대화의 스크린샷을 찍어보세요.",
            ExperiencePoint.from(20), QuestionType.PHOTO,
            StatInfo.of(StatType.PEA, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(16, "좋아하는 디저트 먹기", "자신이 좋아하는 디저트를 먹고, 그 순간의 사진을 찍어보세요.",
	ExperiencePoint.from(20), QuestionType.PHOTO,
	StatInfo.of(StatType.SPI, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(17, "신나는 노래에 맞춰 춤춰보기", "신나는 노래에 맞춰서 15분 동안 춤을 춰보세요. 타이머로 시간을 측정하세요.",
	ExperiencePoint.from(20), QuestionType.TIMER,
	StatInfo.of(StatType.SKL, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(18, "일상에서 느낀 감정/생각을 음악과 함께 적어보기", "그날 느낀 감정이나 생각을 음악과 함께 적어보세요.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(19, "발성 연습하기", "15분 동안 발성 연습을 해보세요. 타이머로 시간을 측정하세요.",
	ExperiencePoint.from(20), QuestionType.TIMER,
	StatInfo.of(StatType.PEA, 1), 1));

        questTemplateRepository.save(
            new QuestTemplate(20, "오늘 하루를 한 줄로 요약해보기", "오늘 하루를 한 줄로 요약해서 적어보세요.",
	ExperiencePoint.from(20), QuestionType.SHORT_ANSWER,
	StatInfo.of(StatType.SPI, 1), 1));
    }
}
