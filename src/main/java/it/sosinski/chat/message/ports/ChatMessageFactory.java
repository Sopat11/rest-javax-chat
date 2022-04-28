package it.sosinski.chat.message.ports;

public interface ChatMessageFactory {

    ChatMessageService messageService(ChatMessageRepository chatMessageRepository);

}
