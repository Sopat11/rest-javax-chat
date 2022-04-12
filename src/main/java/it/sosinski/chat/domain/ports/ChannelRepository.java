package it.sosinski.chat.domain.ports;

import it.sosinski.chat.domain.channel.Channel;

import java.util.List;

public interface ChannelRepository {

    Channel save(Channel channel);

    List<Channel> getAll();
}
