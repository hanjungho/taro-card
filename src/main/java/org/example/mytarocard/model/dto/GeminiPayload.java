package org.example.mytarocard.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GeminiPayload(List<Content> contents) {
    public record Content(String role, List<Part> parts) {}
    public record Part(String text) {}
}
