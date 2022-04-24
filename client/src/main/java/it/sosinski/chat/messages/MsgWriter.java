package it.sosinski.chat.messages;

import it.sosinski.chat.commons.message.ChatMessage;
import it.sosinski.chat.commons.message.MessageType;
import it.sosinski.chat.factory.ProxyFactory;
import lombok.SneakyThrows;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Topic;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;

import static it.sosinski.chat.constants.JndiConstants.CONNECTION_FACTORY_JNDI_NAME;
import static it.sosinski.chat.constants.JndiConstants.MESSAGES_TOPIC_JNDI_NAME;

public class MsgWriter {

    @SneakyThrows
    public void write(String name) throws IOException {
        ProxyFactory proxyFactory = new ProxyFactory();
        ConnectionFactory connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
        Topic topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);

        try (JMSContext jmsContext = connectionFactory.createContext();
             BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                String text = reader.readLine();

                ChatMessage chatMessage = ChatMessage.builder()
                        .channelId(1L)
                        .text(text)
                        .type(MessageType.TEXT)
                        .dateTime(LocalDateTime.now())
                        .sender(name)
                        .build();

                ObjectMessage objectMessage = jmsContext.createObjectMessage(chatMessage);
                objectMessage.setLongProperty("channelId", 1L);
                jmsContext.createProducer().send(topic, objectMessage);
            }
        }
    }
}
