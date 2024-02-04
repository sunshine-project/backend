package com.example.sunshineserver.user.domain;

import com.example.sunshineserver.global.domain.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDate;
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
    private ExperiencePoint exp;
    @Enumerated(value = EnumType.STRING)
    private Level level;

    public User(String name, Gender gender, LocalDate birthDay, CharacterType characterType,
        Stat stat) {
        this.name = name;
        this.gender = gender;
        this.birthDay = birthDay;
        this.characterType = characterType;
        this.stat = stat;
        exp = ExperiencePoint.of(0);
        level = Level.of(1);
    }

    public static User of(String name, Gender gender, LocalDate birthDay,
        CharacterType characterType, Stat stat) {
        return new User(name, gender, birthDay, characterType, stat);
    }
}
