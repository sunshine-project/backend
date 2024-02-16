package com.example.sunshineserver.user.application;


import com.example.sunshineserver.auth.domain.CustomUserDetails;
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

        userPort.save(user);

        return UserCreateResponse.from(request.email());
    }

    public List<User> findAllUsers() {
        return userPort.findAll();
    }

    public UserHomeResponse home(CustomUserDetails customUserDetails)
        throws UserNotFoundedException {
        User user = userPort.findByEmail(customUserDetails.getEmail())
            .orElseThrow(() -> new UserNotFoundedException());

        return UserHomeResponse.from(user);
    }
}
