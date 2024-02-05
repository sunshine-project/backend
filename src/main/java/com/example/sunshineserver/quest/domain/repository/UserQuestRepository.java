package com.example.sunshineserver.quest.domain.repository;

import com.example.sunshineserver.quest.domain.UserQuest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestRepository extends JpaRepository<UserQuest, Long> {

}
