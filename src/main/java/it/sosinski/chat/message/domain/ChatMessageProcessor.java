package it.sosinski.chat.message.domain;

import it.sosinski.chat.commons.message.ChatMessage;
import it.sosinski.chat.message.ports.ChatMessageRepository;
import it.sosinski.chat.message.ports.ChatMessageService;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ChatMessageProcessor implements ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageRepository.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getHistory(Long channelId) {
        return chatMessageRepository.getHistory(channelId);
    }
}
