package com.pskwiercz.springaiud.controller;

import com.pskwiercz.springaiud.model.*;
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

    @PostMapping("/getCapitalInfoResponse")
    public GetCapitalInfoResponse getCapitalInfoResponse(@RequestBody GetCapitalRequest capital) {
        return openAiService.getCapitalInfoResponse(capital);
    }

    @PostMapping("/getCapitalResponse")
    public GetCapitalResponse getCapitalResponse(@RequestBody GetCapitalRequest capital) {
        return openAiService.getCapitalResponse(capital);
    }

    @PostMapping("/getCapitalWithInfo")
    public Answer getCapitalWithInfo(@RequestBody GetCapitalRequest capital) {
        return openAiService.getCapitalWithInfo(capital);
    }

    @PostMapping("/getCapital")
    public Answer getCapital(@RequestBody GetCapitalRequest capital) {
        return openAiService.getCapital(capital);
    }

    @PostMapping("/ask")
    public Answer askQuestion(@RequestBody Question question) {
            return openAiService.getAnswer(question);
    }
}
