package com.example.sunshineserver.quest.infrastructure;

import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.UserQuestPort;
import com.example.sunshineserver.quest.domain.repository.UserQuestRepository;
import com.example.sunshineserver.user.domain.User;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserQuestAdapter implements UserQuestPort {

    private final UserQuestRepository userQuestRepository;

    @Override
    public Optional<UserQuest> findById(Long id) {
        return userQuestRepository.findById(id);
    }

    @Override
    public Long save(UserQuest userQuest) {
        return userQuestRepository.save(userQuest).getUserQuestId();
    }

    @Override
    public List<UserQuest> findAll() {
        return userQuestRepository.findAll();
    }

    @Override
    public void deleteAll() {
        userQuestRepository.deleteAll();
    }

    @Override
    public List<UserQuest> findAllByUser(User user) {
        return userQuestRepository.findAllByUser(user);
    }

    @Override
    public boolean existsByQuestTemplateAndUser(QuestTemplate questTemplate, User user) {
        return userQuestRepository.existsByQuestTemplateAndUser(questTemplate, user);
    }
}
