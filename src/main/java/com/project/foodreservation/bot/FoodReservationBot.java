package com.project.foodreservation.bot;

import com.project.foodreservation.model.entity.Food;
import com.project.foodreservation.model.service.contract.FoodService;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.*;

@Component
public class FoodReservationBot extends TelegramLongPollingBot {

    private final FoodService foodService;

    
    private final Map<Long, Food> foodMap = new HashMap<>();

    private final Map<Long, Map<Long, Integer>> userOrders = new HashMap<>();

    public FoodReservationBot(FoodService foodService) {
        this.foodService = foodService;
    }

    @Override
    public String getBotUsername() {
        return "....YOUR BOT_bot";
    }

    @Override
    public String getBotToken() {
        return "....YOUR TOKEN";
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
            return;
        }

        if (!update.hasMessage()) return;
        Message message = update.getMessage();
        if (!message.hasText()) return;

        String text = message.getText().trim();

        if (text.equalsIgnoreCase("/start")) {

            if (foodMap.isEmpty()) {
                List<Food> foods = foodService.load();
                for (Food f : foods) {
                    foodMap.put(f.getId(), f);
                }
            }

            userOrders.put(message.getChatId(), new HashMap<>());

            sendOrderMenu(message.getChatId(), message.getFrom().getUserName());
        }
    }

    private void handleCallback(CallbackQuery callbackQuery) {
        Long chatId = callbackQuery.getMessage().getChatId();
        String username = callbackQuery.getFrom().getUserName();
        String data = callbackQuery.getData();

        if (data == null) return;

        if (data.equals("DONE")) {
            // todo
            return;
        }

        try {
            Long foodId = Long.parseLong(data);
            Food food = foodMap.get(foodId);
            if (food == null) return;

           
            Map<Long, Integer> order = userOrders.computeIfAbsent(chatId, k -> new HashMap<>());
            order.put(foodId, order.getOrDefault(foodId, 0) + 1);

    
            sendOrderMenu(chatId, username);

        } catch (NumberFormatException ignored) {}
    }

    private void sendOrderMenu(Long chatId, String username) {
        Map<Long, Integer> order = userOrders.get(chatId);

         
        StringBuilder text = new StringBuilder(username + " عزیز، فاکتور شما:\n\n");
        for (Map.Entry<Long, Integer> entry : order.entrySet()) {
            Food f = foodMap.get(entry.getKey());
            text.append("- ").append(f.getName()).append(" x ").append(entry.getValue()).append("\n");
        }
        if (order.isEmpty()) {
            text.append("هیچ غذایی انتخاب نشده است.\n");
        }

        
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();
        for (Food f : foodMap.values()) {
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(f.getId() + " - " + f.getName());
            btn.setCallbackData(f.getId().toString());
            rows.add(Collections.singletonList(btn));
        }

        
        InlineKeyboardButton doneBtn = new InlineKeyboardButton();
        doneBtn.setText("تمام");
        doneBtn.setCallbackData("DONE");
        rows.add(Collections.singletonList(doneBtn));

        markup.setKeyboard(rows);

        SendMessage msg = new SendMessage();
        msg.setChatId(chatId.toString());
        msg.setText(text.toString());
        msg.setReplyMarkup(markup);

        send(msg);
    }

    private void send(SendMessage msg) {
        try {
            execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
