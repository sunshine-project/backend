package com.example.sunshineserver.chat.presentation.dto;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import java.util.List;

public record ChatGptRequest(String model, List<ChatMessage> messages, Double temperature) {

    private static String prefixMessage = "";
    public static ChatCompletionRequest from(String text) {
        return ChatCompletionRequest.builder()
            .model("gpt-3.5-turbo")
            .messages(List.of(new ChatMessage("user", prefixMessage + text)))
            .temperature(0.7)
            .build();
    }
}