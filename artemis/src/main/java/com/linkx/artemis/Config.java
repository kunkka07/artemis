package com.linkx.artemis;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConverter;

@Configuration
@EnableJms
public class Config {
    @Bean
    public ActiveMQConnectionFactory getConnection() {
        var connection = new ActiveMQConnectionFactory("tcp://localhost:61616");
        connection.setUser("artemis");
        connection.setPassword("simetraehcapa");
        System.out.println(connection.getClientID());
        return connection;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        var template = new JmsTemplate(getConnection());
        template.setSessionTransacted(true);
        return template;
    }

    @Bean
    public JmsTransactionManager jmsTransactionManager() {
        var transaction = new JmsTransactionManager(getConnection());
        return transaction;
    }

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory() {
        DefaultJmsListenerContainerFactory fact = new DefaultJmsListenerContainerFactory();
        fact.setConnectionFactory(getConnection());
        fact.setTransactionManager(jmsTransactionManager());
        fact.setConcurrency("1-1");
        fact.setSessionTransacted(true);
        fact.setMessageConverter(messageConverter());
        return fact;
    }

    @Bean
    public MessageConverter messageConverter() {
        return new CustomMessageConverter();
    }

}
