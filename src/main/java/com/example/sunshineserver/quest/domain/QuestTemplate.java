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
    String title;
    String description;

    @Embedded
    ExperiencePoint experiencePoint;

    @Enumerated(EnumType.STRING)
    QuestionType questionType;

    @Embedded
    StatInfo statInfo;

    private Integer timeLimit;
    private boolean requiresPhoto;
    private boolean requiresReflection;

    public QuestTemplate(int questionDay, String title, String description,
        ExperiencePoint experiencePoint, QuestionType questionType, StatInfo statInfo,
        Integer timeLimit, boolean requiresPhoto, boolean requiresReflection) {
        this.questionDay = questionDay;
        this.title = title;
        this.description = description;
        this.experiencePoint = experiencePoint;
        this.questionType = questionType;
        this.statInfo = statInfo;
        this.timeLimit = timeLimit;
        this.requiresPhoto = requiresPhoto;
        this.requiresReflection = requiresReflection;
    }
}
