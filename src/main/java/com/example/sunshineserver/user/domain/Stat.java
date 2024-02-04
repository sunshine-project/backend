package com.example.sunshineserver.user.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stat {

    private int STR;
    private int SPI;
    private int PEA;
    private int SKL;

    public Stat(int STR, int SPI, int PEA, int SKL) {
        this.STR = STR;
        this.SPI = SPI;
        this.PEA = PEA;
        this.SKL = SKL;
    }

    public static Stat of(final int STR, final int SPI, final int PEA, final int SKL) {
        return new Stat(STR, SPI, PEA, SKL);
    }
}
