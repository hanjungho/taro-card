package org.example.mytarocard.model.dto;

import org.example.mytarocard.model.constant.LLMModel;

public record LLMServiceParam(LLMModel model, String prompt) {
}
