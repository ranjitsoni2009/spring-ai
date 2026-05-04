package com.learning.spring.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Consumer;

import static com.learning.spring.ai.util.OpenAIConstant.hrPolicySystemPrompt;

/**
 * Created by Ranjit Soni on 02-05-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@RestController
@RequestMapping("/api/openai")
public class OpenAIPromptTemplateController {

    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/emailPrompt.st")
    Resource userPromptTemplate;

    public OpenAIPromptTemplateController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/email")
    public String getMessage(@RequestParam("customerName") String customerName, @RequestParam("message") String message) {

        return this.chatClient.prompt()
                .user(promptUserSpec -> {
                    promptUserSpec.text(userPromptTemplate)
                            .param("customerName", customerName)
                            .param("customerMessage", message);
                })
                .call()
                .content();
    }
}
