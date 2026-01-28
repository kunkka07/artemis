package com.linkx.artemis;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @JmsListener(destination = "test-queue", containerFactory = "jmsListenerContainerFactory")
    public void onMessage(String message) {
        System.out.println("Received message: " + message);
    }
}
