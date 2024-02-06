package com.example.sunshineserver.user.domain;

import com.example.sunshineserver.global.domain.BaseEntity;
import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.StatInfo;
import com.example.sunshineserver.quest.domain.UserQuest;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;
    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private LocalDate birthDay;

    @Enumerated(value = EnumType.STRING)
    private CharacterType characterType;

    @Embedded
    private Stat stat;

    @Embedded
    private ExperiencePoint experiencePoint = ExperiencePoint.initial();

    @Enumerated(value = EnumType.STRING)
    private Level level = Level.LV1;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserQuest> userQuests = new ArrayList<>();

    public User(String name, Gender gender, LocalDate birthDay, CharacterType characterType,
        Stat stat) {
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.characterType = characterType;
        this.stat = stat;
    }

    public static User of(String name, Gender gender, LocalDate birthDay,
        CharacterType characterType, Stat stat) {
        return new User(name, gender, birthDay, characterType, stat);
    }

    public void completeQuest(QuestTemplate questTemplate) {
        addExperiencePoint(questTemplate.getExperiencePoint());
        addStat(questTemplate.getStatInfo());
    }

    public void addStat(StatInfo statInfo) {
        this.stat = stat.add(statInfo);
    }

    public void addExperiencePoint(ExperiencePoint questExperiencePoint) {
        this.experiencePoint = experiencePoint.add(questExperiencePoint);
        calculateLevel();
    }

    public void calculateLevel() {
        this.level = Level.from(experiencePoint.get());
    }

    public boolean isQuestExisted() {
        return userQuests.stream()
            .anyMatch(UserQuest::isUnchecked);
    }

    public boolean isAbleToEndGame() {
        return stat.isAbleToEndGame();
    }
}
