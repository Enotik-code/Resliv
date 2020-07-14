package payroll.telegram;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import payroll.strings.StringFile;
import payroll.web.dao.service.MessageService;

@Service
public class BotService {

    @Autowired
    private Bot bot;

    @Autowired
    private MessageService messageService;

    private static final Logger log = Logger.getLogger(BotService.class);


    public synchronized void sendMessage(Message message, String text) {
        try {
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(message.getChatId().toString());
            sendMessage.setText(getAnswer(text));
            bot.execute(sendMessage);
        } catch (TelegramApiException e) {
            log.error(e.toString());
        }
    }

    public String getAnswer(String text){
        return (text.equals("/start")) ? getAnswerIfStartMessage() : messageService.getAnswer(text);
    }

    public String getAnswerIfStartMessage() {
        return StringFile.START_INFO;
    }
}
