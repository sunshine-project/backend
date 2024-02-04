package com.example.sunshineserver.user.service;


import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserPort;
import com.example.sunshineserver.user.presentation.dto.UserCreateRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserPort userPort;

    public User create(UserCreateRequest request) {
        User user = User.of(request.name(),
            request.gender(),
            request.birthDay(),
            request.characterType(),
            request.stat());
        return userPort.save(user);
    }
}
