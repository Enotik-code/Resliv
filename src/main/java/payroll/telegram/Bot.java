package payroll.telegram;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import payroll.MessageService;


public class Bot extends TelegramLongPollingBot {

    static Logger log = Logger.getLogger(Bot.class.getName());

    @Autowired
    private MessageService messageService;

    @Override
    public void onUpdateReceived(Update update) {
        String message = update.getMessage().getText();
        sendMsg(update.getMessage().getChatId().toString(), message);
    }

    public synchronized void sendMsg(String chatId, String s) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);
        sendMessage.setChatId(chatId);
        sendMessage.setText(messageService.getAnswer(s));
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error("Exception: " + e.toString());
        }
    }

    @Override
    public String getBotUsername() {
        return "ReslivInterviewBot";
    }

    @Override
    public String getBotToken() {
        return "1056753107:AAG-Sx6ZOBozVoD8LpvW2Y2XkDcjJbd9I6g";
    }


}