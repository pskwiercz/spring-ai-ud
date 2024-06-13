package com.pskwiercz.springaiud.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pskwiercz.springaiud.model.Answer;
import com.pskwiercz.springaiud.model.GetCapitalRequest;
import com.pskwiercz.springaiud.model.GetCapitalResponse;
import com.pskwiercz.springaiud.model.Question;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OpenAiServiceImpl implements OpenAiService {

    private final ChatClient chatClient;

    @Value("classpath:templates/get-capital-prompt.st")
    private Resource getCapitalPrompt;

    @Value("classpath:templates/get-capital-with-info-prompt.st")
    private Resource getCapitalWithInfoPrompt;

    @Value("classpath:templates/get-capital-json-answer-prompt.st")
    private Resource getCapitalJsonPrompt;

    @Value("classpath:templates/get-capital-response-prompt.st")
    private Resource getCapitalResponsePrompt;

    @Autowired
    private ObjectMapper objectMapper;

    public OpenAiServiceImpl(ChatClient.Builder chatClient) {
        this.chatClient = chatClient.build();
    }

    @Override
    public GetCapitalResponse getCapitalResponse(GetCapitalRequest getCapitalRequest) {

        BeanOutputConverter<GetCapitalResponse> converter = new BeanOutputConverter<>(GetCapitalResponse.class);

        Prompt prompt = new PromptTemplate(getCapitalResponsePrompt)
                .create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry(),
                        "format", converter.getFormat()));

        String answer = chatClient
                .prompt(prompt)
                .call()
                .content();

        return converter.convert(answer);
    }

    @Override
    public Answer getCapitalWithInfo(GetCapitalRequest getCapitalRequest) {

        Prompt prompt = new PromptTemplate(getCapitalWithInfoPrompt)
                .create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));

        String answer = chatClient
                .prompt(prompt)
                .call()
                .content();

        return new Answer(answer);
    }

    @Override
    public Answer getCapital(GetCapitalRequest getCapitalRequest) {

        Prompt prompt = new PromptTemplate(getCapitalJsonPrompt)
                .create(Map.of("stateOrCountry", getCapitalRequest.stateOrCountry()));

        String answer = chatClient
                .prompt(prompt)
                .call()
                .content();

        String responseString;
        try {
            JsonNode jsonNode = objectMapper.readTree(answer);
            responseString = jsonNode.get("answer").asText();
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return new Answer(responseString);
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
