package org.example.cs322project.service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class GeminiClient {

    @Value("${gemini.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    private final String endpoint = "https://generativelanguage.googleapis.com/v1beta/models/gemini-pro:generateContent?key=";

    public String getGeminiPlan(String prompt) {
        String url = endpoint + apiKey;

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> body = Map.of(
                "contents", new Object[] {
                        Map.of("parts", new Object[] {
                                Map.of("text", prompt)
                        })
                }
        );

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);

        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            Map content = (Map) ((Map) ((java.util.List) response.getBody().get("candidates")).get(0)).get("content");
            java.util.List parts = (java.util.List) content.get("parts");
            return (String) ((Map) parts.get(0)).get("text");
        } catch (Exception e) {
            return "Error talking to Gemini API: " + e.getMessage();
        }
    }
}
