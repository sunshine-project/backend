package com.example.sunshineserver.quest.infrastructure;

import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.UserQuestPort;
import com.example.sunshineserver.quest.domain.repository.UserQuestRepository;
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
        return userQuestRepository.save(userQuest).getId();
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
    public List<UserQuest> findCompletedQuests(Long userId) {
        return userQuestRepository.findByIsCompletedTrueAndUserId(userId);
    }

    @Override
    public List<UserQuest> findUncompletedQuests(Long userId) {
        return userQuestRepository.findByIsCompletedFalseAndUserId(userId);
    }
}
