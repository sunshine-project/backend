package com.example.sunshineserver.quest.domain.repository;

import com.example.sunshineserver.quest.domain.UserQuest;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface UserQuestPort {

    Optional<UserQuest> findById(Long id);

    Long save(UserQuest userQuest);

    List<UserQuest> findAll();

    void deleteAll();

    List<UserQuest> findCompletedQuests(Long userId);

    List<UserQuest> findUncompletedQuests(Long aLong);
}
