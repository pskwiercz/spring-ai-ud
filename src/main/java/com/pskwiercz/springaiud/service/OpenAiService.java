package com.pskwiercz.springaiud.service;

import com.pskwiercz.springaiud.model.*;

public interface OpenAiService {

    String getAnswer(String question);
    Answer getAnswer(Question question);
    Answer getCapital(GetCapitalRequest question);
    Answer getCapitalWithInfo(GetCapitalRequest question);
    GetCapitalResponse getCapitalResponse(GetCapitalRequest question);
    GetCapitalInfoResponse getCapitalInfoResponse(GetCapitalRequest getCapitalRequest);
}
