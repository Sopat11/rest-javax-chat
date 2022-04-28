package it.sosinski.chat.channel.domain.ports;

import it.sosinski.chat.channel.domain.channel.Channel;
import it.sosinski.chat.channel.domain.channel.NewChannel;

import java.util.List;

public interface ChannelService {

    List<Channel> getAll();
    Channel save(NewChannel newChannel);
}
