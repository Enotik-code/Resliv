package payroll.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import payroll.web.dao.service.MessageService;

@Service
public class BotService {

    @Autowired
    private Bot bot;

    @Autowired
    private MessageService messageService;

    public synchronized void sendMessage(Message message, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setText(messageService.getAnswer(text));
        try {
            bot.execute(sendMessage);
        }
        catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

}
