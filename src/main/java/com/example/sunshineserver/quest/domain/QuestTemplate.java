package com.example.sunshineserver.quest.domain;

import com.example.sunshineserver.user.domain.ExperiencePoint;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quest_template_id")
    private Long id;

    @Column(unique = true)
    private int questionDay;

    @NotNull
    String title;

    @NotNull
    String description;

    @Embedded
    ExperiencePoint experiencePoint;

    @Enumerated(EnumType.STRING)
    QuestionType questionType;

    @Embedded
    StatInfo statInfo;

    private Integer timeLimit;

    public QuestTemplate(int questionDay, String title, String description,
        ExperiencePoint experiencePoint, QuestionType questionType, StatInfo statInfo,
        Integer timeLimit) {
        this.questionDay = questionDay;
        this.title = title;
        this.description = description;
        this.experiencePoint = experiencePoint;
        this.questionType = questionType;
        this.statInfo = statInfo;
        this.timeLimit = timeLimit;
    }

    public boolean isShortAnswerQuest() {
        return questionType == QuestionType.SHORT_ANSWER;
    }
}
