package com.example.sunshineserver.saramin;

import com.example.sunshineserver.chat.application.ChatService;
import com.example.sunshineserver.saramin.application.SaraminService;
import com.example.sunshineserver.saramin.presentation.dto.Job;
import com.example.sunshineserver.saramin.presentation.dto.SaraminJobRequest;
import com.example.sunshineserver.saramin.presentation.dto.SaraminJobResponse;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SaraminServiceTest {

    @Autowired
    private SaraminService saraminService;

    @Autowired
    private ChatService chatService;

    @Test
    void 사용자의_기록을_보고_직업을_추천한다() {
        // given
        String text = "나는 개발자가 되고 싶어요";
        // when
        SaraminJobRequest saraminInquiryRequest = chatService.generateSaraminRequest(text);
        List<SaraminJobResponse> responses = saraminService.inquire(saraminInquiryRequest);

        // then
        Assertions.assertThat(responses).isNotEmpty();
    }
}
