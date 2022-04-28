package it.sosinski.chat.channel.adapters.persistance;

import it.sosinski.chat.channel.domain.Channel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface JpaPersistenceChannelMapper {

    ChannelEntity toEntity(Channel channel);
    Channel toDomain(ChannelEntity channelEntity);

}
