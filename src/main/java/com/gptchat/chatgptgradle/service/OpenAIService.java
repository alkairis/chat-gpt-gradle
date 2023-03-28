package com.gptchat.chatgptgradle.service;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class OpenAIService {
    @Autowired
    private RestTemplate restTemplate;

    @Value("${openai.api.key}")
    private String apiKey;

    public String generateText(String prompt) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(apiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        String url = "https://api.openai.com/v1/completions";

        Map<String, Object> requestBody = new HashMap<>();

        requestBody.put("prompt", prompt);
        requestBody.put("model", "text-davinci-003");
        requestBody.put("max_tokens", 3000);
        requestBody.put("temperature",0);
        requestBody.put("top_p",1);
        requestBody.put("frequency_penalty",0.5);
        requestBody.put("presence_penalty",0);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<JsonNode> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, JsonNode.class);

        String generatedText = responseEntity.getBody().get("choices").get(0).get("text").asText();

        return generatedText;
    }
}
