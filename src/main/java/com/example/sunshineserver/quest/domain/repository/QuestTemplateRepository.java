package com.example.sunshineserver.quest.domain.repository;

import com.example.sunshineserver.quest.domain.QuestTemplate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestTemplateRepository extends JpaRepository<QuestTemplate, Long> {

    Optional<QuestTemplate> findByQuestionDay(int daysFromCreation);
}
