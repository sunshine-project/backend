package com.example.sunshineserver.user.infrastructure;

import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserRepository;
import com.example.sunshineserver.user.domain.repository.UserPort;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class UserAdapter implements UserPort {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public Long save(User user) {
        return userRepository.save(user).getId();
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
