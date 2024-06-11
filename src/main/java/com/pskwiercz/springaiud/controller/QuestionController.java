package com.pskwiercz.springaiud.controller;

import com.pskwiercz.springaiud.model.Answer;
import com.pskwiercz.springaiud.model.Question;
import com.pskwiercz.springaiud.service.OpenAiService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {

    private final OpenAiService openAiService;

    public QuestionController(OpenAiService openAiService) {
        this.openAiService = openAiService;
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
            return openAiService.getAnswer(question);
    }
}
