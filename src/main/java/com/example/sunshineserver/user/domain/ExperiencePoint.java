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

    public ExperiencePoint(int experiencePoint) {
        this.experiencePoint = experiencePoint;
    }

    public static ExperiencePoint of(final int experiencePoint) {
        return new ExperiencePoint(experiencePoint);
    }

    public int getExperiencePoint() {
        return experiencePoint;
    }
}
