package it.sosinski.chat.message;

import it.sosinski.chat.message.domain.BasicChatMessageFactory;
import it.sosinski.chat.message.ports.ChatMessageFactory;
import it.sosinski.chat.message.ports.ChatMessageRepository;
import it.sosinski.chat.message.ports.ChatMessageService;

import javax.enterprise.inject.Produces;
import javax.inject.Singleton;

@Singleton
public class ChatMessageConfig {

    private static final ChatMessageFactory MESSAGE_FACTORY = new BasicChatMessageFactory();

    @Singleton
    @Produces
    public ChatMessageService messageService(ChatMessageRepository chatMessageRepository) {
        return MESSAGE_FACTORY.messageService(chatMessageRepository);
    }
}
