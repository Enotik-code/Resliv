package payroll.web.dao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import payroll.strings.StringFile;
import payroll.web.bean.Message;
import payroll.web.dao.LoadDatabase;
import payroll.web.dao.repository.MessageRepository;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    public List<Message> list() {
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
