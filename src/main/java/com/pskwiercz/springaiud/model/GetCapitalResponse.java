package com.pskwiercz.springaiud.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record GetCapitalResponse(@JsonPropertyDescription("The name of city") String answer) {
}
