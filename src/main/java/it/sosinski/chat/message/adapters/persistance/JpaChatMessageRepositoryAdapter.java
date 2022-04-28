package it.sosinski.chat.message.adapters.persistance;

import it.sosinski.chat.commons.message.ChatMessage;
import it.sosinski.chat.message.ports.ChatMessageRepository;
import lombok.RequiredArgsConstructor;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class JpaChatMessageRepositoryAdapter implements ChatMessageRepository {

    private final JpaChatMessageRepository chatMessageRepository;
    private final JpaPersistenceChatMessageMapper chatMessageMapper;

    @Override
    public ChatMessage save(ChatMessage chatMessage) {
        var chatMessageEntity = chatMessageMapper.toEntity(chatMessage);
        var savedEntity = chatMessageRepository.save(chatMessageEntity);
        return chatMessageMapper.toDomain(savedEntity);
    }

    @Override
    public List<ChatMessage> getHistory(Long channelId) {
        List<ChatMessageEntity> chatMessageEntities = chatMessageRepository.getHistory(channelId);
        return chatMessageEntities.stream()
                .map(chatMessageMapper::toDomain)
                .collect(Collectors.toList());
    }
}
