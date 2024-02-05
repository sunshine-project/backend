package com.example.sunshineserver.user.domain.repository;

import com.example.sunshineserver.user.domain.User;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Component;

@Component
public interface UserPort {

    Optional<User> findById(Long id);

    Long save(User user);

    List<User> findAll();

    void deleteAll();
}
