package com.pskwiercz.springaiud.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class OpenAiServiceImplTest {

    @Autowired
    OpenAiService openAiService;

    @Test
    public void getAnswer() {

        String answer = openAiService.getAnswer("Tell me joke about nerds");
        System.out.println(answer);
    }
}