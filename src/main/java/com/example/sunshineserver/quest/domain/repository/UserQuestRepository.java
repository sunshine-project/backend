package com.example.sunshineserver.quest.domain.repository;

import com.example.sunshineserver.quest.domain.UserQuest;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserQuestRepository extends JpaRepository<UserQuest, Long> {

    List<UserQuest> findByIsCheckedFalseAndUserId(Long userId);

    List<UserQuest> findByIsCompletedTrueAndUserId(Long userId);

    List<UserQuest> findByIsCompletedFalseAndUserId(Long userId);
}
