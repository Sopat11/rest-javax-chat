package it.sosinski.chat.message.ports;

import it.sosinski.chat.commons.message.ChatMessage;

import java.util.List;

public interface ChatMessageService {

    ChatMessage save(ChatMessage chatMessage);

    List<ChatMessage> getHistory(Long channelId);
}
