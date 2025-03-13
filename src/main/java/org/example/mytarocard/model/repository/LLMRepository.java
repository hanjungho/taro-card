package org.example.mytarocard.model.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import org.example.mytarocard.model.constant.LLMModel;
import org.example.mytarocard.model.dto.*;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class LLMRepository {
    private LLMRepository() {}
    private final static LLMRepository instance = new LLMRepository();
    public static LLMRepository getInstance() {
        return instance;
    }

    private final Logger logger = Logger.getLogger(LLMRepository.class.getName());
    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final Dotenv dotenv = Dotenv.configure().ignoreIfMissing().load();
    // 나중에 수정이 필요하면 overloading 여러개의 파람.
    public  String callModel(LLMModel model, String prompt) throws IOException, InterruptedException {
        String url = switch (model.platform) {
            case GEMINI -> "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=%s".formatted(dotenv.get("GEMINI_KEY"));
            case TOGETHER -> "https://api.together.xyz/v1/images/generations";
            default -> throw new RuntimeException("Unknown platform: " + model.platform);
        };
        String[] headers = switch (model.platform) {
            case GEMINI -> new String[]{"Content-Type", "application/json"};
            case TOGETHER -> new String[]{
                    "Authorization", "Bearer %s".formatted(dotenv.get("TOGETHER_KEY")),
                    "Content-Type", "application/json"};
            default -> throw new IllegalStateException("Unexpected platform: " + model.platform);
        };
        String body = switch (model.modelName) {
            case "gemini-2.0-flash" -> mapper.writeValueAsString(new GeminiPayload(
                    List.of(new GeminiPayload.Content("user", List.of(
                            new GeminiPayload.Part(prompt))))
            ));
            case "together-image" -> mapper.writeValueAsString(new ImageLLMBody("black-forest-labs/FLUX.1-schnell-Free", prompt, 1024, 768, 4, 1, "url"));
            default -> throw new RuntimeException("Unexpected model: " + model);
        };
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .method("POST", HttpRequest.BodyPublishers.ofString(body))
                .headers(headers)
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        logger.info("%d".formatted(response.statusCode()));
        if (response.statusCode() >= 400) {
            logger.info(response.body());
        }
        switch (model.modelName) {
            case "gemini-2.0-flash" -> {
                return mapper.readValue(response.body(), GeminiResponse.class).candidates().get(0).content().parts().get(0).text();
            }
            case "together-image" -> {
                return mapper.readValue(response.body(), ImageLLMResponse.class).data().get(0).url();
            }
            default -> throw new RuntimeException("Unexpected model: " + model);
        }
//        return response.body();
    }
}
