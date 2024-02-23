package com.example.sunshineserver.quest.domain.repository;

import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface UserQuestPort {

    Optional<UserQuest> findById(Long id);

    Long save(UserQuest userQuest);

    List<UserQuest> findAll();

    void deleteAll();

    List<UserQuest> findAllByUser(User user);

    boolean existsByQuestTemplateAndUser(QuestTemplate questTemplate, User user);
}
