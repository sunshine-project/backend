package com.example.sunshineserver.user.domain;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.AccessType;

@Getter
@Setter
@Embeddable
@EqualsAndHashCode
@AccessType(AccessType.Type.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Stat {

    private int str;
    private int spi;
    private int pea;
    private int skl;

    public Stat(int str, int spi, int pea, int skl) {
        this.str = str;
        this.spi = spi;
        this.pea = pea;
        this.skl = skl;
    }

    public static Stat of(final int STR, final int SPI, final int PEA, final int SKL) {
        return new Stat(STR, SPI, PEA, SKL);
    }
}
