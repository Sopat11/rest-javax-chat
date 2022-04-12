package it.sosinski.chat.adapters.persistance;

import it.sosinski.chat.domain.channel.Channel;
import org.mapstruct.Mapper;

@Mapper(componentModel = "cdi")
public interface JpaPersistenceChannelMapper {

    ChannelEntity toEntity(Channel channel);
    Channel toDomain(ChannelEntity channelEntity);

}
