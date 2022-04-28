package it.sosinski.chat.message.adapters.rest;

import it.sosinski.chat.commons.message.ChatMessage;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface RestChatMessageMapper {

    @IterableMapping(elementTargetType = ChatMessageDto.class)
    List<ChatMessageDto> toDto(List<ChatMessage> chatMessages);
    ChatMessageDto toDto(ChatMessage chatMessage);
    ChatMessage toDomain(ChatMessageDto chatMessageDto);
}
