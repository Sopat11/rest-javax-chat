package it.sosinski.chat.message.ports;

import it.sosinski.chat.commons.message.ChatMessage;

public interface ChatMessageService {

    ChatMessage save(ChatMessage chatMessage);
}
