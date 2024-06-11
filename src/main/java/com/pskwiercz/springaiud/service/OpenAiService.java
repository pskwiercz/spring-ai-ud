package com.pskwiercz.springaiud.service;

import com.pskwiercz.springaiud.model.Answer;
import com.pskwiercz.springaiud.model.GetCapitalRequest;
import com.pskwiercz.springaiud.model.Question;

public interface OpenAiService {

    String getAnswer(String question);

    Answer getAnswer(Question question);

    Answer getCapital(GetCapitalRequest question);
}
