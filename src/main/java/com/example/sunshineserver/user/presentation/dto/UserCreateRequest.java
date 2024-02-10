package com.example.sunshineserver.user.presentation.dto;

import com.example.sunshineserver.user.domain.CharacterType;
import com.example.sunshineserver.user.domain.Gender;
import com.example.sunshineserver.user.domain.Stat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDate;

public record UserCreateRequest(String email, String name, Gender gender, @JsonFormat(pattern = "yyyyMMdd") LocalDate birthDay,
		CharacterType characterType, Stat stat) {

}
