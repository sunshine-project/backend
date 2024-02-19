package com.example.sunshineserver.user.domain;

import com.example.sunshineserver.global.domain.BaseEntity;
import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.StatInfo;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
import java.util.Objects;
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

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private LocalDate birthDay;

    private String refreshToken;

    @Enumerated(value = EnumType.STRING)
    private CharacterType characterType;

    @Embedded
    private Stat stat;

    @Embedded
    private ExperiencePoint experiencePoint = ExperiencePoint.initial();

    @Enumerated(value = EnumType.STRING)
    private Level level = Level.LV1;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<UserQuest> userQuests = new ArrayList<>();

    public User(String email, String name, Gender gender, LocalDate birthDay,
        CharacterType characterType,
        Stat stat) {
        this.email = email;
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.characterType = characterType;
        this.stat = stat;
        this.role = Role.USER;
    }

    public static User of(String email, String name, Gender gender, LocalDate birthDay,
        CharacterType characterType, Stat stat) {
        return new User(email, name, gender, birthDay, characterType, stat);
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

    public long uncompletedQuestSize() {
        return userQuests.stream()
            .filter(UserQuest::isUncompleted)
            .count();
    }

    public boolean isAbleToEndGame() {
        return stat.isAbleToEndGame();
    }

    public boolean isRefreshTokenValid(String refreshToken) {
        return this.refreshToken.equals(refreshToken);
    }

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
