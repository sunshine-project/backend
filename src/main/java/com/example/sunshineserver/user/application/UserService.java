package com.example.sunshineserver.user.application;


import com.example.sunshineserver.global.exception.UserAlreadyExistedException;
import com.example.sunshineserver.global.exception.UserNotFoundedException;
import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserPort;
import com.example.sunshineserver.user.presentation.dto.UserCreateRequest;
import com.example.sunshineserver.user.presentation.dto.UserCreateResponse;
import com.example.sunshineserver.user.presentation.dto.UserHomeResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserPort userPort;

    @Transactional
    public UserCreateResponse create(UserCreateRequest request) {
        if (userPort.findByEmail(request.email()).isPresent()) {
            throw new UserAlreadyExistedException();
        }

        User user = User.of(request.email(),
            request.name(),
            request.gender(),
            request.birthDay(),
            request.characterType(),
            request.stat());
        Long userId = userPort.save(user);
        return UserCreateResponse.from(userId);
    }

    public List<User> findAllUsers() {
        return userPort.findAll();
    }

    public UserHomeResponse home(Long userId) throws UserNotFoundedException {
        User user = userPort.findById(userId)
            .orElseThrow(() -> new UserNotFoundedException());

        return UserHomeResponse.from(user);
    }
}
