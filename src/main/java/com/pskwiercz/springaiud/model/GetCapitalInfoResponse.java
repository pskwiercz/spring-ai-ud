package com.pskwiercz.springaiud.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalInfoResponse(
        @JsonPropertyDescription("The name of city") String answer,
        @JsonPropertyDescription("The official language") String language,
        @JsonPropertyDescription("The official currency") String currency) {
}
