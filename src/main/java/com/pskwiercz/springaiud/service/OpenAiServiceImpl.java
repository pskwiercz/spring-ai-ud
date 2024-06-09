package com.pskwiercz.springaiud.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    private final ChatClient chatClient;

    public OpenAiServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @Override
    public String getAnswer(String question) {

        return chatClient
                .prompt()
                .user(question)
                .call()
                .content();
    }
}
