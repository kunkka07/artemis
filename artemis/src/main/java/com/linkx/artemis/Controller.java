package com.linkx.artemis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
    @Autowired
    private JmsTemplate jmsTemplate;

    @GetMapping("send")
    @Transactional
    public String sendMessage(String message) {
        jmsTemplate.convertAndSend("test-queue", message);
        return "Message sent.";
    }
}
