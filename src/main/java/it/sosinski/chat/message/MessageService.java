package it.sosinski.chat.message;

import it.sosinski.chat.commons.message.ChatMessage;
import lombok.SneakyThrows;
import lombok.extern.java.Log;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(activationConfig = {
        @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
        @ActivationConfigProperty(propertyName = "destination", propertyValue = "Messages")
})
@Log
public class MessageService implements MessageListener {

    @SneakyThrows
    @Override
    public void onMessage(Message message) {
        var chatMessage = message.getBody(ChatMessage.class);
        log.info("" + chatMessage);
    }

}
