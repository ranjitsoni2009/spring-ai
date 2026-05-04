package com.learning.spring.ai.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
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
public class OpenAIPromptTemplateStuffingController {

    private final ChatClient chatClient;

    @Value("classpath:/promptTemplates/emailPrompt.st")
    Resource userPromptTemplate;

    public OpenAIPromptTemplateStuffingController(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    @GetMapping("/hr-policy")
    public String getHRPolicy(@RequestParam("query") String query) {
        return this.chatClient.prompt()
                .advisors(new SimpleLoggerAdvisor())
                .system(hrPolicySystemPrompt) //Prompt Templat Stuffing
                .user(query)
                .call()
                .content();
    }
}
