package com.project.foodreservation.config;

import com.project.foodreservation.bot.FoodReservationBot;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

@Component
public class BotInitializer {

    private final FoodReservationBot foodReservationBot;

    public BotInitializer(FoodReservationBot foodReservationBot) {
        this.foodReservationBot = foodReservationBot;
    }

    @PostConstruct
    public void init() throws Exception {
        TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
        botsApi.registerBot(foodReservationBot);
    }
}
