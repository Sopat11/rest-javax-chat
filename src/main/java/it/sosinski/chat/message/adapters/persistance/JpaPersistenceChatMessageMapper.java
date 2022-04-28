package it.sosinski.chat.message.adapters.persistance;

import it.sosinski.chat.commons.message.ChatMessage;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface JpaPersistenceChatMessageMapper {

    ChatMessageEntity toEntity(ChatMessage chatMessage);
    ChatMessage toDomain(ChatMessageEntity chatMessageEntity);

}
