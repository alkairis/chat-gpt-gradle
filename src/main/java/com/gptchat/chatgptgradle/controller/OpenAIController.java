package com.gptchat.chatgptgradle.controller;

import com.gptchat.chatgptgradle.service.OpenAIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
public class OpenAIController {
    @Autowired
    private OpenAIService openAIService;

    @PostMapping("/generate-text")
    public ResponseEntity<Map> generateText(@RequestBody Map prompt) {
        String generatedText = openAIService.generateText((String) prompt.get("prompt"));
        HashMap<String,String> map=new HashMap<String,String>();
        map.put("resp", generatedText);
        return ResponseEntity.ok(map);
    }
}