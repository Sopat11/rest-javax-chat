package it.sosinski.chat.domain.ports;

import it.sosinski.chat.domain.channel.Channel;
import it.sosinski.chat.domain.channel.NewChannel;

import java.util.List;

public interface ChannelService {

    List<Channel> getAll();
    Channel save(NewChannel newChannel);
}
