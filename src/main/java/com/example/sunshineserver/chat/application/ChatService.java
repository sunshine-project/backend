package com.example.sunshineserver.chat.application;

import com.example.sunshineserver.chat.presentation.dto.ChatGptRequest;
import com.example.sunshineserver.chat.presentation.dto.ChatGptResponse;
import com.example.sunshineserver.saramin.presentation.dto.SaraminJobRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.service.OpenAiService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatService {

    private final OpenAiService openAiService;
    private final ObjectMapper objectMapper;

    @Transactional
    public SaraminJobRequest generateSaraminRequest(String text) {
        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(
            ChatGptRequest.from(text));

        String response = ChatGptResponse.of(chatCompletion).messages().get(0).message();
        try {
            SaraminJobRequest request = objectMapper.readValue(response,
	SaraminJobRequest.class);
            return request;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid response");
        }
    }
}
