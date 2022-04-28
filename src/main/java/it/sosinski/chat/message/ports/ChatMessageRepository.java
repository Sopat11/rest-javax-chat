package it.sosinski.chat.message.ports;

import it.sosinski.chat.commons.message.ChatMessage;

public interface ChatMessageRepository {

    ChatMessage save(ChatMessage chatMessage);

}
