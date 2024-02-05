package com.example.sunshineserver.quest.presentation.dto;

import com.example.sunshineserver.quest.domain.QuestionType;
import com.example.sunshineserver.quest.domain.StatInfo;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.user.domain.ExperiencePoint;

public record QuestDetailResponse(int questionDay, String title, String description,
		  ExperiencePoint experiencePoint, StatInfo statInfo,
		  QuestionType questionType, int timeLimit) {

    public static QuestDetailResponse of(UserQuest userQuest) {
        return new QuestDetailResponse(
            userQuest.getQuestTemplate().getQuestionDay(),
            userQuest.getQuestTemplate().getTitle(),
            userQuest.getQuestTemplate().getDescription(),
            userQuest.getQuestTemplate().getExperiencePoint(),
            userQuest.getQuestTemplate().getStatInfo()
            , userQuest.getQuestTemplate().getQuestionType(),
            userQuest.getQuestTemplate().getTimeLimit()
        );
    }
}
