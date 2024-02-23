package com.example.sunshineserver.quest.application;

import com.example.sunshineserver.auth.domain.CustomUserDetails;
import com.example.sunshineserver.chat.application.ChatService;
import com.example.sunshineserver.global.exception.QuestNotExistedException;
import com.example.sunshineserver.global.exception.UserNotFoundedException;
import com.example.sunshineserver.quest.domain.QuestTemplate;
import com.example.sunshineserver.quest.domain.UserQuest;
import com.example.sunshineserver.quest.domain.repository.UserQuestPort;
import com.example.sunshineserver.quest.infrastructure.PixelConverter;
import com.example.sunshineserver.quest.presentation.dto.QuestDetailResponse;
import com.example.sunshineserver.quest.presentation.dto.ShortAnswerQuestCompleteRequest;
import com.example.sunshineserver.saramin.application.SaraminService;
import com.example.sunshineserver.saramin.presentation.dto.SaraminJobRequest;
import com.example.sunshineserver.saramin.presentation.dto.SaraminJobResponse;
import com.example.sunshineserver.user.domain.User;
import com.example.sunshineserver.user.domain.repository.UserPort;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class QuestService {

    private final UserQuestPort userQuestPort;
    private final UserPort userPort;
    private final PixelConverter pixelConverter;
    private final Storage storage;
    private final ChatService chatService;
    private final SaraminService saraminService;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Transactional
    public void complete(Long questId, CustomUserDetails userDetails) {
        User currentUser = userPort.findById(userDetails.getId())
            .orElseThrow(() -> new UserNotFoundedException());

        UserQuest userQuest = userQuestPort.findById(questId)
            .orElseThrow(() -> new QuestNotExistedException());

        if (!userQuest.isUserMatched(currentUser)) {
            throw new UserNotFoundedException();
        }
        userQuest.completeShortAnswer();
    }

    @Transactional
    public String completeShortAnswerQuest(Long questId, ShortAnswerQuestCompleteRequest request,
        CustomUserDetails userDetails) {
        User currentUser = userPort.findById(userDetails.getId())
            .orElseThrow(() -> new UserNotFoundedException());

        UserQuest userQuest = userQuestPort.findById(questId)
            .orElseThrow(() -> new QuestNotExistedException());

        if (!userQuest.isUserMatched(currentUser)) {
            throw new UserNotFoundedException();
        }
        userQuest.completeShortAnswer(request.answer());

        return request.answer();
    }

    @Transactional
    public String completePhotoQuest(Long questId, MultipartFile photo,
        CustomUserDetails userDetails) {
        User currentUser = userPort.findById(userDetails.getId())
            .orElseThrow(() -> new UserNotFoundedException());

        UserQuest userQuest = userQuestPort.findById(questId)
            .orElseThrow(() -> new QuestNotExistedException());

        if (!userQuest.isUserMatched(currentUser)) {
            throw new UserNotFoundedException();
        }

        String uuid = UUID.randomUUID().toString();
        String type = photo.getContentType();

        uploadPixelatedImage(photo, 3, uuid, type);
        userQuest.completePhoto(uuid);

        return uuid;
    }

    private void uploadPixelatedImage(MultipartFile file, int pixSize,
        String uuid, String type) {
        try {
            BufferedImage img = pixelConverter.pixelateImage(file, pixSize);

            // BufferedImage를 바이트 배열로 변환
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(img, "jpg", baos);
            byte[] imgBytes = baos.toByteArray();

            // 바이트 배열을 ByteArrayInputStream으로 변환
            ByteArrayInputStream bais = new ByteArrayInputStream(imgBytes);

            // 변환된 이미지 스트림을 사용하여 GCP Cloud Storage에 업로드
            BlobInfo blobInfo = storage.create(
	BlobInfo.newBuilder(bucketName, uuid)
	    .setContentType(type)
	    .build(),
	bais // 이미지 데이터
            );
        } catch (IOException error) {
            throw new IllegalArgumentException(
	"Failed to upload pixelated image to GCP Cloud Storage");
        }
    }

    public List<UserQuest> findCompletedQuests(CustomUserDetails userDetails) {
        User user = userPort.findByEmail(userDetails.getEmail())
            .orElseThrow(() -> new UserNotFoundedException());

        return userQuestPort.findAllByUser(user).stream()
            .filter(UserQuest::isCompleted)
            .collect(Collectors.toList());
    }

    public List<UserQuest> findUncompletedQuests(CustomUserDetails userDetails) {
        User user = userPort.findByEmail(userDetails.getEmail())
            .orElseThrow(() -> new UserNotFoundedException());

        List<UserQuest> userQuests = userQuestPort.findAllByUser(user).stream()
            .filter(UserQuest::isUncompleted)
            .collect(Collectors.toList());

        return userQuests;
    }

    public QuestDetailResponse findQuestDetail(Long questId) {
        UserQuest userQuest = userQuestPort.findById(questId)
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 퀘스트입니다."));

        return QuestDetailResponse.of(userQuest);
    }

    @Transactional
    public void assignQuest(User user, QuestTemplate questTemplate) {
        userQuestPort.save(UserQuest.of(questTemplate, user));
    }

    public List<SaraminJobResponse> findFinalQuest(CustomUserDetails userDetails) {
        User user = userPort.findByEmail(userDetails.getEmail())
            .orElseThrow(() -> new UserNotFoundedException());

        String text = userQuestPort.findAllByUser(user)
            .stream()
            .filter(u -> u.getQuestTemplate().isShortAnswerQuest())
            .map(u -> u.getShortAnswer())
            .collect(Collectors.joining());

        SaraminJobRequest request = chatService.generateSaraminRequest(text);
        return saraminService.inquire(request);
    }
}
