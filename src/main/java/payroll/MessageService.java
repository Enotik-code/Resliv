package payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public List<Message> list() {
        return messageRepository.findAll();
    }

    public List<Message> getMessageByCityName(String cityName){
        return messageRepository.getMessageByName(cityName);
    }

    public String getAnswer(String cityName){
        return messageRepository.getMessageByName(cityName).toString();
    }

}
