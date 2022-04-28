package it.sosinski.chat.channel.ports;

import it.sosinski.chat.channel.domain.Channel;

import java.util.List;

public interface ChannelRepository {

    Channel save(Channel channel);

    List<Channel> getAll();
}
