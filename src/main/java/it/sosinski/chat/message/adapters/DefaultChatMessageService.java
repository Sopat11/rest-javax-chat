package it.sosinski.chat.message.adapters;

import it.sosinski.chat.channel.adapters.Basic;
import it.sosinski.chat.commons.message.ChatMessage;
import it.sosinski.chat.message.ports.ChatMessageService;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import java.util.List;

@Basic
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class DefaultChatMessageService implements ChatMessageService {

    private final ChatMessageService chatMessageService;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        return chatMessageService.save(chatMessage);
    }

    @Override
    public List<ChatMessage> getHistory(Long channelId) {
        return chatMessageService.getHistory(channelId);
    }
}
