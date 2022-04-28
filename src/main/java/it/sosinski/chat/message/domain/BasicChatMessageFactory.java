package it.sosinski.chat.message.domain;

import it.sosinski.chat.message.ports.ChatMessageFactory;
import it.sosinski.chat.message.ports.ChatMessageRepository;
import it.sosinski.chat.message.ports.ChatMessageService;

public class BasicChatMessageFactory implements ChatMessageFactory {

    @Override
    public ChatMessageService messageService(ChatMessageRepository chatMessageRepository) {
        return new ChatMessageProcessor(chatMessageRepository);
    }
}
