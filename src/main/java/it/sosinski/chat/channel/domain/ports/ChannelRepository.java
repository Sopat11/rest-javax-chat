package it.sosinski.chat.channel.domain.ports;

import it.sosinski.chat.channel.domain.channel.Channel;

import java.util.List;

public interface ChannelRepository {

    Channel save(Channel channel);

    List<Channel> getAll();
}
