package payroll.web.dao.service;

import lombok.SneakyThrows;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import payroll.strings.StringFile;
import payroll.web.bean.Message;
import payroll.web.dao.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;


    private static final Logger log = Logger.getLogger(MessageService.class);

    @SneakyThrows
    public List<Message> list(){
        return messageRepository.findAll();
    }

    public String getAnswer(String cityName) {
        String answer = null;
        try {
            answer = messageRepository.getMessageByName(cityName).getDescription();
        } catch (Exception e) {
            log.info(StringFile.NO_INFORMATION);
        }
        return (answer != null) ? answer: StringFile.NO_INFORMATION;
    }

}
