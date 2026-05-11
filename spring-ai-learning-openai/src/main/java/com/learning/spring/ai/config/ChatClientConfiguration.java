package com.learning.spring.ai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Ranjit Soni on 03-05-2026.
 * Author: ranjitsoni2009@gmail.com
 */

@Configuration
public class ChatClientConfiguration {

    /*
     * By Default Spring AI configure Single ChatClient Builder as Bean
     * Commenting default behaviour
     * @param builder
     * @return

    @Bean
    public ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }
     */

    /**
     * This is specific for OpenAI Chat Model
     * @param openAiChatModel
     * @return
     */
    @Bean
    public ChatClient chatClientForOpenAI(OpenAiChatModel openAiChatModel) {
        return ChatClient.create(openAiChatModel);
    }
}
