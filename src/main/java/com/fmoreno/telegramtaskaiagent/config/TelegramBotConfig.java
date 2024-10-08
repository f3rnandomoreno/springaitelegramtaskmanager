package com.fmoreno.telegramtaskaiagent.config;

import com.fmoreno.telegramtaskaiagent.client.TelegramClientConfig;
import jakarta.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@Configuration
@Log4j2
public class TelegramBotConfig {

    @Value("${telegram.bot.token}")
    protected String botToken;

    @Autowired
    ChatClient chatClient;

    @PostConstruct
    public void init() {
        try {
            TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication();
            botsApplication.registerBot(botToken, new TelegramClientConfig(botToken,chatClient));
            log.trace("Bot registered successfully");
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
