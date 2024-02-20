package com.example.sunshineserver.user.domain;

import com.example.sunshineserver.quest.domain.StatInfo;
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

    public Stat add(StatInfo statInfo) {
        switch (statInfo.getStatType()) {
            case STR:
	return new Stat(this.str + statInfo.getStatValue(), this.spi, this.pea, this.skl);
            case SPI:
	return new Stat(this.str, this.spi + statInfo.getStatValue(), this.pea, this.skl);
            case PEA:
	return new Stat(this.str, this.spi, this.pea + statInfo.getStatValue(), this.skl);
            case KNO:
	return new Stat(this.str, this.spi, this.pea, this.skl + statInfo.getStatValue());
            default:
	throw new IllegalArgumentException("잘못된 스탯 타입입니다.");
        }
    }

    public boolean isAbleToEndGame() {
        return this.str >= 50 && this.spi >= 50 && this.pea >= 50 && this.skl >= 50;
    }
}
