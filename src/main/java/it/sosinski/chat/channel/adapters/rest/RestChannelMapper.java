package it.sosinski.chat.channel.adapters.rest;

import it.sosinski.chat.channel.domain.channel.Channel;
import it.sosinski.chat.channel.domain.channel.NewChannel;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface RestChannelMapper {

    @IterableMapping(elementTargetType = ChannelDto.class)
    List<ChannelDto> toDto(List<Channel> channels);
    ChannelDto toDto(Channel channel);
    NewChannel toDomain(NewChannelDto newChannelDto);
}
