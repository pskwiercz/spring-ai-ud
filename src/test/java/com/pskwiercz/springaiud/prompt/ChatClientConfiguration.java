package com.pskwiercz.springaiud.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ChatClientConfiguration {

    @Autowired
    ChatClient.Builder builder;

    @Bean
    public ChatClient getChatClient() {
        return builder.build();
    }
}
