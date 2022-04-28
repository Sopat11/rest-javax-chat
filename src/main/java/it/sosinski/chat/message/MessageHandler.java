package it.sosinski.chat.message;

import it.sosinski.chat.channel.adapters.Basic;
import it.sosinski.chat.commons.message.ChatMessage;
import it.sosinski.chat.message.ports.ChatMessageService;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages")
})
@Log
public class MessageHandler implements MessageListener {

    @Basic
    @Inject
    private ChatMessageService chatMessageService;

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        var chatMessage = message.getBody(ChatMessage.class);
        ChatMessage savedChatMessage = chatMessageService.save(chatMessage);
        log.info("" + savedChatMessage);
    }

}
