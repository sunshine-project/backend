package com.example.sunshineserver.user.application;


import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserPort;
import com.example.sunshineserver.user.presentation.dto.UserCreateRequest;
import com.example.sunshineserver.user.presentation.dto.UserHomeResponse;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserPort userPort;

    public Long create(UserCreateRequest request) {
        User user = User.of(request.name(),
            request.gender(),
            request.birthDay(),
            request.characterType(),
            request.stat());
        return userPort.save(user);
    }

    public List<User> findAllUsers() {
        return userPort.findAll();
    }

    public UserHomeResponse findHome(Long userId) {
        User user = userPort.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        return UserHomeResponse.from(user);
    }
}
