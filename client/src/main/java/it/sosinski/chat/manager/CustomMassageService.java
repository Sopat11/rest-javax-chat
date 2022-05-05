package it.sosinski.chat.manager;

import it.sosinski.chat.commons.channel.CurrentChannel;
import it.sosinski.chat.commons.message.ChatMessage;
import it.sosinski.chat.commons.message.MessageType;
import it.sosinski.chat.config.ProxyFactory;
import it.sosinski.chat.utils.*;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Topic;

import static it.sosinski.chat.constants.JndiConstants.CONNECTION_FACTORY_JNDI_NAME;
import static it.sosinski.chat.constants.JndiConstants.MESSAGES_TOPIC_JNDI_NAME;
import static it.sosinski.chat.manager.ServerMessagesConstants.*;

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

        if (!ChannelUtils.isOnChannel(currentChannel)) {
            ServerPrinter.print(NOT_CONNECTED_TO_CHANNEL);
            return;
        } else {
            if (!CommandsUtils.isSendingFile(text)) {

                chatMessage = ChatMessage.builder()
                        .sender(name)
                        .channelId(currentChannel.getId())
                        .type(MessageType.TEXT)
                        .text(text)
                        .build();
            } else {
                if (!TextUtils.hasTwoParentheses(text)) {
                    ServerPrinter.print(NO_FILEPATH);
                    return;
                }

                String filePath = TextUtils.getTextFromParentheses(text);
                if (!FileUtils.doesFileExist(filePath)) {
                    ServerPrinter.print(NO_SUCH_FILE);
                    return;
                }

                chatMessage = FileUtils.encodeFile(filePath, name, currentChannel.getId());
            }
        }

        jmsContext.createProducer().send(topic, chatMessage);
        jmsContext.close();
    }
}
