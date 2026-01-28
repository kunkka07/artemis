package com.linkx.artemis;

import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.SimpleMessageConverter;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.Session;

public class CustomMessageConverter implements MessageConverter {

    private final SimpleMessageConverter del = new SimpleMessageConverter();

    @Override
    public Message toMessage(Object object, Session session) throws JMSException, MessageConversionException {
        return del.toMessage(object, session);
    }

    @Override
    public Object fromMessage(Message message) throws JMSException, MessageConversionException {

        return del.fromMessage(message);
    }
}
