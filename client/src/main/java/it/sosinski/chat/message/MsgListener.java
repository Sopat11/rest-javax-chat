package it.sosinski.chat.message;

import it.sosinski.chat.commons.channel.CurrentChannel;
import it.sosinski.chat.commons.message.ChatMessage;
import it.sosinski.chat.commons.message.MessageType;
import it.sosinski.chat.config.ProxyFactory;
import it.sosinski.chat.utils.FileUtils;
import lombok.extern.java.Log;

import javax.jms.*;
import javax.naming.NamingException;

import static it.sosinski.chat.constants.JndiConstants.CONNECTION_FACTORY_JNDI_NAME;
import static it.sosinski.chat.constants.JndiConstants.MESSAGES_TOPIC_JNDI_NAME;

@Log
public class MsgListener implements Runnable {

    private CurrentChannel currentChannel;

    public MsgListener(CurrentChannel currentChannel) {
        this.currentChannel = currentChannel;
    }

    private final MessageListener onMessage = message -> {
        try {
            ChatMessage chatMessage = message.getBody(ChatMessage.class);
            if (chatMessage.getChannelId().equals(currentChannel.getId())) {
                if (chatMessage.getType() == MessageType.TEXT){
                    System.out.println(chatMessage);
                } else {
                    FileUtils.decodeFile(chatMessage);
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    };

    public void run() {

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
             JMSConsumer consumer = jmsContext.createConsumer(topic)) {
            consumer.setMessageListener(onMessage);

            while (true) {

            }
        }
    }
}

