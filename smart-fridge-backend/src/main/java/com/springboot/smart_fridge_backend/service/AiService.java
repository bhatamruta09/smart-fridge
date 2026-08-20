package com.springboot.smart_fridge_backend.service;

import com.springboot.smart_fridge_backend.model.Ingredient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AiService {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestClient restClient = RestClient.create();

    public String suggestRecipes(List<Ingredient> ingredients) {
        String ingredientList = ingredients.stream()
                .map(i -> i.getName() + " (" + i.getQuantity() + " " + i.getUnit() + ")")
                .collect(Collectors.joining(", "));

        String prompt = "I have these ingredients: " + ingredientList +
                ". Suggest 2 simple recipes I can make using mainly these ingredients. " +
                "Format your response in Markdown exactly like this for each recipe: " +
                "a '### ' heading with the recipe title, followed by a numbered list (1., 2., 3.) " +
                "of 3-4 brief steps. Do not use bold text for step labels. Keep it concise, no extra commentary.";

        String url = "https://generativelanguage.googleapis.com/v1beta/models/gemini-flash-latest:generateContent?key=" + apiKey;

        Map<String, Object> requestBody = Map.of(
                "contents", List.of(
                        Map.of("parts", List.of(
                                Map.of("text", prompt)
                        ))
                )
        );

        Map<String, Object> response = callWithRetry(url, requestBody, 3);
        return extractText(response);
    }

    @SuppressWarnings("unchecked")
    private String extractText(Map<String, Object> response) {
        try {
            List<Map<String, Object>> candidates = (List<Map<String, Object>>) response.get("candidates");
            Map<String, Object> content = (Map<String, Object>) candidates.get(0).get("content");
            List<Map<String, Object>> parts = (List<Map<String, Object>>) content.get("parts");
            return (String) parts.get(0).get("text");
        } catch (Exception e) {
            return "Sorry, I couldn't generate a suggestion right now.";
        }
    }
    
    private Map<String, Object> callWithRetry(String url, Map<String, Object> requestBody, int maxAttempts) {
        for (int attempt = 1; attempt <= maxAttempts; attempt++) {
            try {
                return restClient.post()
                        .uri(url)
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON)
                        .body(requestBody)
                        .retrieve()
                        .body(Map.class);
            } catch (org.springframework.web.client.HttpServerErrorException e) {
                if (attempt == maxAttempts) {
                    throw e;
                }
                try {
                    Thread.sleep(1500L * attempt);
                } catch (InterruptedException ignored) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        throw new RuntimeException("Failed to get AI response after retries");
    }
}