package com.learning.spring.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static com.learning.spring.ai.util.OpenAIConstant.hrPolicySystemPrompt;

/**
 * Created by Ranjit Soni on 02-05-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@RestController
@RequestMapping("/api/openai")
public class OpenAIChatController {

    private final ChatClient chatClient;

    public OpenAIChatController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/chat")
    public String getMessage(@RequestParam("query") String query) {
        return this.chatClient.prompt()
                .user(query)
                .call()
                .content();
    }
}
