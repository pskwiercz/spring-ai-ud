package com.pskwiercz.springaiud.prompt;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = ChatClientConfiguration.class)
public class BaseTestClass {

    @Autowired
    ChatClient openAiChatClient;

    String chat(String prompt) {

        return openAiChatClient
                .prompt(new PromptTemplate(prompt).create())
                .call()
                .content();
    }
}