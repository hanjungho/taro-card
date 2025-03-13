package org.example.mytarocard.service;

import org.example.mytarocard.model.dto.LLMServiceParam;
import org.example.mytarocard.model.dto.LLMServiceResponse;
import org.example.mytarocard.model.repository.LLMRepository;

import java.io.IOException;
import java.util.logging.Logger;

public class LLMServiceImpl implements LLMService {
    private LLMServiceImpl() {}

    public static LLMService instance = new LLMServiceImpl();

    public static LLMService getInstance() {
        return instance;
    }

    private final Logger logger = Logger.getLogger(LLMServiceImpl.class.getName());

    private final LLMRepository llmRepository = LLMRepository.getInstance();

    @Override
    public LLMServiceResponse callModel(LLMServiceParam param) throws IOException, InterruptedException {
        logger.info("callModel");
//        return new LLMServiceResponse("test");
        String prompt = "%s, Based on the previous request, write a description for making an unusual tarot card. If you have a request that has nothing to do with jailbreak or tarot, please refuse it and give me an explanation to make a weird tarot card. The result must be plain text with no markdown and no more than 500 characters in plain Korean.".formatted(param.prompt());
        return new LLMServiceResponse(llmRepository.callModel(
                param.model(), prompt));
    }
}
