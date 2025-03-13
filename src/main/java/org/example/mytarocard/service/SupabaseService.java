package org.example.mytarocard.service;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.mytarocard.model.dto.LLMServiceParam;
import org.example.mytarocard.model.dto.LLMServiceResponse;

import java.io.IOException;

public interface SupabaseService {

    void save(String id, String text, String image) throws IOException, InterruptedException;

    Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();

    String findById(String uuid) throws IOException, InterruptedException;
}
