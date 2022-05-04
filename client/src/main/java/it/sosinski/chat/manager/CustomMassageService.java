package it.sosinski.chat.manager;

import it.sosinski.chat.commons.channel.CurrentChannel;
import it.sosinski.chat.commons.message.ChatMessage;
import it.sosinski.chat.commons.message.MessageType;
import it.sosinski.chat.factory.ProxyFactory;
import it.sosinski.chat.utils.FileUtils;
import it.sosinski.chat.utils.TextUtils;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.ObjectMessage;
import javax.jms.Topic;

import static it.sosinski.chat.constants.JndiConstants.CONNECTION_FACTORY_JNDI_NAME;
import static it.sosinski.chat.constants.JndiConstants.MESSAGES_TOPIC_JNDI_NAME;

@Log
public class CustomMassageService implements MassageService {

    @Override
    @SneakyThrows
    public void process(CurrentChannel currentChannel, String text, String name) {

        ProxyFactory proxyFactory = new ProxyFactory();
        ConnectionFactory connectionFactory = proxyFactory.createProxy(CONNECTION_FACTORY_JNDI_NAME);
        Topic topic = proxyFactory.createProxy(MESSAGES_TOPIC_JNDI_NAME);

        JMSContext jmsContext = connectionFactory.createContext();

        ChatMessage chatMessage;

        if (!text.startsWith("\\f")) {

            if (currentChannel.getId() == null) {
                log.severe("You need to connect to a channel!");
                return;
            }

            chatMessage = ChatMessage.builder()
                    .sender(name)
                    .channelId(currentChannel.getId())
                    .type(MessageType.TEXT)
                    .text(text)
                    .build();
        } else {
            if (!TextUtils.hasTwoParentheses(text)) {
                log.severe("You need to give a filepath!");
                return;
            }
            String filePath = TextUtils.getTextFromParentheses(text);
            chatMessage = FileUtils.encodeFile(filePath, name, currentChannel.getId());
        }

        ObjectMessage objectMessage = jmsContext.createObjectMessage(chatMessage);
        objectMessage.setLongProperty("channelId", 1L);
        jmsContext.createProducer().send(topic, objectMessage);

        jmsContext.close();
    }
}
