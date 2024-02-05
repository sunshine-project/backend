package com.example.sunshineserver.user.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ExperiencePoint {

    private int experiencePoint;

    private ExperiencePoint(int experiencePoint) {
        this.experiencePoint = experiencePoint;
    }

    public static ExperiencePoint from(final int experiencePoint) {
        return new ExperiencePoint(experiencePoint);
    }

    public static ExperiencePoint initial() {
        return new ExperiencePoint(0);
    }

    public int get() {
        return experiencePoint;
    }

    public ExperiencePoint add(ExperiencePoint questExperiencePoint) {
        return ExperiencePoint.from(this.experiencePoint + questExperiencePoint.experiencePoint);
    }

}
