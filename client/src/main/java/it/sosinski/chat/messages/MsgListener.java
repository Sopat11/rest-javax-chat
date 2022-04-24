package it.sosinski.chat.messages;

import it.sosinski.chat.commons.message.ChatMessage;
import it.sosinski.chat.factory.ProxyFactory;
import lombok.extern.java.Log;

import javax.jms.*;
import javax.naming.NamingException;

@Log
public class MsgListener implements Runnable {

    private static final String CONNECTION_FACTORY_JNDI_NAME = "jms/RemoteConnectionFactory";
    private static final String MESSAGES_TOPIC_JNDI_NAME = "jms/topic/Messages";

    private static final MessageListener onMessage = message -> {
        try {
            ChatMessage chatMessage = message.getBody(ChatMessage.class);
            System.out.println(chatMessage);
        } catch (JMSException e) {
            e.printStackTrace();
        }
    };

    public void run() {
        Long channelId = 1L;

        ProxyFactory proxyFactory;
        ConnectionFactory connectionFactory = null;
        Topic topic = null;

        try {
            proxyFactory = new ProxyFactory();
            connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
            topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);
        } catch (NamingException e) {
            e.printStackTrace();
        }

        try (JMSContext jmsContext = connectionFactory.createContext();
             JMSConsumer consumer = jmsContext.createConsumer(topic, "channelId = " + channelId)) {
            consumer.setMessageListener(onMessage);

            while (true) {

            }
        }
    }
}

