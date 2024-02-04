package com.example.sunshineserver.user.presentation.dto;

import com.example.sunshineserver.user.domain.CharacterType;
import com.example.sunshineserver.user.domain.Gender;
import com.example.sunshineserver.user.domain.Stat;
import java.time.LocalDate;

public record UserCreateRequest(String name, Gender gender, LocalDate birthDay,
		CharacterType characterType, Stat stat) {

}
