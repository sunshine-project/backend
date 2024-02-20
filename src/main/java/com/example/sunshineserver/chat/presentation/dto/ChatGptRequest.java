package com.example.sunshineserver.chat.presentation.dto;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import java.util.List;

public record ChatGptRequest(String model, List<ChatMessage> messages, Double temperature) {

    private static String prefixMessage = "사용자의 주관식 답변을 모아놨어. 여기에서 사용자의 관심사를 추려서 직무코드와 키워드를 반환해줘."
        + "직무코드는 다음과 같아. (1 - 서비스업, 2 - 제조/화학, 3 - IT, 4 - 은행/금융업, 5 - 미디어/디자인, 6 - 교육업, 7 - 의료 제약/복지, 8 - 판매/유통, 9 - 건설업, 10 - 기관/협회"
        + "키워드는 사용자의 취향에 맞게 추려서 반환해줘"
        + "이 때 반환 값은 JSON 형태로 반환하고, 직무 코드는 ind_cd, 키워드는 keyword로 반환해줘";

    public static ChatCompletionRequest from(String text) {
        return ChatCompletionRequest.builder()
            .model("gpt-3.5-turbo")
            .messages(List.of(new ChatMessage("user", prefixMessage + text)))
            .temperature(0.7)
            .build();
    }
}