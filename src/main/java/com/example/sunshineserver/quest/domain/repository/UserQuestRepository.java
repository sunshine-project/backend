package com.example.sunshineserver.quest.domain.repository;

import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.user.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserQuestRepository extends JpaRepository<UserQuest, Long> {

    List<UserQuest> findByUserId(Long userId);
    boolean existsByQuestTemplateAndUser(QuestTemplate questTemplate, User user);

    List<UserQuest> findAllByUser(User user);
}
