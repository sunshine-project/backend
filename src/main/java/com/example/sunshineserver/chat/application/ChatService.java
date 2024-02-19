package com.example.sunshineserver.chat.application;

import com.example.sunshineserver.chat.presentation.dto.ChatGptRequest;
import com.example.sunshineserver.chat.presentation.dto.ChatGptResponse;
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

    @Transactional
    public ChatGptResponse generateKeyword(String text) {
        ChatCompletionResult chatCompletion = openAiService.createChatCompletion(
            ChatGptRequest.from(text));

        return ChatGptResponse.of(chatCompletion);
    }
}
