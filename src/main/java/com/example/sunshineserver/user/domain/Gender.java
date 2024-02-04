package com.example.sunshineserver.user.domain;

public enum Gender {
    MALE(0), FEMALE(1);

    int type;

    Gender(int type) {
        this.type = type;
    }
}
