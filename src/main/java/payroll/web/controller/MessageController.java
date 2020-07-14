package payroll.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import payroll.web.dao.repository.MessageRepository;
import payroll.web.dao.service.MessageService;

@Controller
public class MessageController {

    @Autowired
    private final MessageService messageService;

    MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping(value = "/home")
    public ModelAndView viewHomePage(){
        ModelAndView modelAndView = new ModelAndView("home");
        modelAndView.addObject("messageList", messageService.list());
        return modelAndView;
    }


}
