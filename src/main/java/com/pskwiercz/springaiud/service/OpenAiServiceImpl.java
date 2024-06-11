package com.pskwiercz.springaiud.service;

import com.pskwiercz.springaiud.model.Answer;
import com.pskwiercz.springaiud.model.GetCapitalRequest;
import com.pskwiercz.springaiud.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    private final ChatClient chatClient;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    public OpenAiServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @Override
    public Answer getCapital(GetCapitalRequest getCapitalRequest) {

        Prompt prompt = new PromptTemplate(getCapitalPrompt)
                .create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));

        String answer = chatClient
                .prompt(prompt)
                .call()
                .content();

        return new Answer(answer);
    }

    @Override
    public Answer getAnswer(Question question) {

        String answer = chatClient
                .prompt()
                .user(question.question())
                .call()
                .content();

        return new Answer(answer);
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
