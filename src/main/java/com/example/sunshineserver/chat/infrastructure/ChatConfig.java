package com.example.sunshineserver.chat.infrastructure;

import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ChatConfig {

    @Value("${gpt.token}")
    private String token;

    @Bean
    public OpenAiService openAiService() {
        return new OpenAiService(token, Duration.ofSeconds(60));
    }
}
