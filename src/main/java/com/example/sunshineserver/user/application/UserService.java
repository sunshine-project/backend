package com.example.sunshineserver.user.application;


import com.example.sunshineserver.auth.domain.CustomUserDetails;
import com.example.sunshineserver.global.exception.UserAlreadyExistedException;
import com.example.sunshineserver.global.exception.UserNotFoundedException;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.UserQuestRepository;
import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserPort;
import com.example.sunshineserver.user.presentation.dto.UserCreateRequest;
import com.example.sunshineserver.user.presentation.dto.UserCreateResponse;
import com.example.sunshineserver.user.presentation.dto.UserHomeResponse;
import com.example.sunshineserver.user.presentation.dto.UserMypageResponse;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserPort userPort;
    private final UserQuestRepository userQuestRepository;

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

    public List<UserMypageResponse> findAlbum(CustomUserDetails customUserDetails) {
        return userQuestRepository.findByUser_Id(customUserDetails.getId())
            .stream()
            .filter(u -> u.getQuestTemplate().isPhotoQuest())
            .map(m -> new UserMypageResponse(m.getQuestTemplate().getTitle(), m.getPhotoUrl()))
            .collect(Collectors.toList());
    }

    public List<UserMypageResponse> findJournal(CustomUserDetails customUserDetails) {
        return userQuestRepository.findByUser_Id(customUserDetails.getId())
            .stream()
            .filter(u -> u.getQuestTemplate().isShortAnswerQuest())
            .map(m -> new UserMypageResponse(m.getQuestTemplate().getTitle(), m.getShortAnswer()))
            .collect(Collectors.toList());
    }
}
