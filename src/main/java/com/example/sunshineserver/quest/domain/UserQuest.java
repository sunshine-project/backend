package com.example.sunshineserver.quest.domain;

import com.example.sunshineserver.global.domain.BaseEntity;
import com.example.sunshineserver.global.exception.QuestionTypeMismatchException;
import com.example.sunshineserver.quest.presentation.dto.ShortAnswerQuestCompleteRequest;
import com.example.sunshineserver.user.domain.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserQuest extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_quest_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private QuestTemplate questTemplate;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    boolean isCompleted = false;
    private String photoUrl = null;
    private String shortAnswer = null;

    public void completeShortAnswer() {
        if (isCompleted) {
            throw new IllegalStateException("이미 완료된 퀘스트입니다.");
        }
        isCompleted = true;
        user.completeQuest(questTemplate);
    }

    public void completeShortAnswer(String shortAnswer) {
        if (isCompleted) {
            throw new IllegalStateException("이미 완료된 퀘스트입니다.");
        }

        if (!questTemplate.isShortAnswerQuest()) {
            throw new QuestionTypeMismatchException();
        }
        this.isCompleted = true;
        this.shortAnswer = shortAnswer;
        this.user.completeQuest(questTemplate);
    }

    public void completePhoto(String photoUrl) {
        if (isCompleted) {
            throw new IllegalStateException("이미 완료된 퀘스트입니다.");
        }

        if (!questTemplate.isPhotoQuest()) {
            throw new QuestionTypeMismatchException();
        }
        this.isCompleted = true;
        this.photoUrl = photoUrl;
        this.user.completeQuest(questTemplate);
    }

    private UserQuest(QuestTemplate questTemplate, User user) {
        this.questTemplate = questTemplate;
        this.user = user;
    }

    public static UserQuest of(QuestTemplate questTemplate, User user) {
        return new UserQuest(questTemplate, user);
    }


    public boolean isUncompleted() {
        return !isCompleted;
    }

    public boolean isUserMatched(User currentUser) {
        return this.user.equals(currentUser);
    }
}
